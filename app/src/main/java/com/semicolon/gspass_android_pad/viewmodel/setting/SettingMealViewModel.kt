package com.semicolon.gspass_android_pad.viewmodel.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.semicolon.gspass_android_pad.data.local.SharedPreferenceStorage
import com.semicolon.gspass_android_pad.data.remote.setting.SettingApiImpl
import com.semicolon.gspass_android_pad.model.GradeMealData
import com.semicolon.gspass_android_pad.model.SetMealTimeRequest

class SettingMealViewModel(
    private val sharedPreferenceStorage: SharedPreferenceStorage,
    private val settingApiImpl: SettingApiImpl
) : ViewModel() {

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> get() = _toastMessage

    private val _isElementSchool = MutableLiveData(true)
    val isElementSchool: LiveData<Boolean> get() = _isElementSchool

    private val _back = MutableLiveData(false)
    val back: LiveData<Boolean> get() = _back

    private val _breakFastCheck = MutableLiveData(false)
    val breakFastCheck: LiveData<Boolean> get() = _breakFastCheck

    private val _lunchCheck = MutableLiveData(false)
    val lunchCheck: LiveData<Boolean> get() = _lunchCheck

    private val _dinnerCheck = MutableLiveData(false)
    val dinnerCheck: LiveData<Boolean> get() = _dinnerCheck

    val gradeMeals = MutableLiveData<ArrayList<GradeMealData>>()

    val editGrade = MutableLiveData<Int>()

    fun loadSchoolType() {
        val schoolName = sharedPreferenceStorage.getInfo("school_name")
        _isElementSchool.value = schoolName.contains("초등학교")
        _breakFastCheck.value = sharedPreferenceStorage.getInfo("break_fast_check", false)
        _lunchCheck.value = sharedPreferenceStorage.getInfo("launch_check", false)
        _dinnerCheck.value = sharedPreferenceStorage.getInfo("dinner_check", false)
        loadMeals()
    }

    private fun loadMeals() {
        val repeat: Int = if (isElementSchool.value!!) {
            6
        } else {
            3
        }
        for (ct in 1..repeat) {
            val breakfastContent = "breakfast$ct"
            val breakfast = "아침: " + sharedPreferenceStorage.getInfo(breakfastContent)

            val lunchContent = "lunch$ct"
            val lunch = "점심: " + sharedPreferenceStorage.getInfo(lunchContent)

            val dinnerContent = "dinner$ct"
            val dinner = "저녁: " + sharedPreferenceStorage.getInfo(dinnerContent)

            val gradeMeal = GradeMealData(breakfast, lunch, dinner)
            gradeMeals.value?.set(ct, gradeMeal)
        }
    }

    private fun saveMeals(){
        val repeat: Int = if (isElementSchool.value!!) {
            6
        } else {
            3
        }
        for(ct in 1..repeat){
            val breakfastContent = "breakfast$ct"
            gradeMeals.value?.get(ct)
                ?.let { sharedPreferenceStorage.saveInfo(it.breakfast,breakfastContent) }

            val lunchContent = "lunch$ct"
            gradeMeals.value?.get(ct)
                ?.let { sharedPreferenceStorage.saveInfo(it.lunch,lunchContent) }

            val dinnerContent = "dinner$ct"
            gradeMeals.value?.get(ct)
                ?.let { sharedPreferenceStorage.saveInfo(it.dinner,dinnerContent) }
        }
    }

    fun sendMealData(grade: Int){
        val accessToken = sharedPreferenceStorage.getInfo("access_token")
        val meal = gradeMeals.value?.get(grade)
        val request = SetMealTimeRequest(grade,meal?.breakfast?:"00:00:00",meal?.lunch?:"00:00:00",meal?.dinner?:"00:00:00")
        settingApiImpl.setMealTime(accessToken, request).subscribe { response->
            if(response.code()==204){
                _toastMessage.value = "업데이트 하였습니다"
                saveMeals()

            }else{
                _toastMessage.value = "오류가 발생하였습니다"
            }
        }
    }

    fun editGrade(grade:Int){
        editGrade.value = grade
    }

    fun backPressed() {
        _back.value = true
    }
}