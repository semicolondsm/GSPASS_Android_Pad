package com.semicolon.gspass_android_pad.viewmodel.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.semicolon.gspass_android_pad.data.local.SharedPreferenceStorage
import com.semicolon.gspass_android_pad.data.remote.setting.SettingApiImpl
import com.semicolon.gspass_android_pad.model.GradeMealData
import com.semicolon.gspass_android_pad.model.SetMealTimeRequest
import kotlin.properties.Delegates

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

    val gradeMeals = MutableLiveData(HashMap<Int, GradeMealData>())

    val editGrade = MutableLiveData<Int>()

    private var repeat by Delegates.notNull<Int>()

    fun loadSchoolType() {
        val schoolName = sharedPreferenceStorage.getInfo("school_name")
        _isElementSchool.value = schoolName.contains("초등학교")
        _breakFastCheck.value = sharedPreferenceStorage.getInfo("break_fast_check", false)
        _lunchCheck.value = sharedPreferenceStorage.getInfo("launch_check", false)
        _dinnerCheck.value = sharedPreferenceStorage.getInfo("dinner_check", false)
        repeat = if (isElementSchool.value!!) {
            6
        } else {
            3
        }
        loadMeals(repeat)
    }

    fun loadMeals(repeat: Int) {
        for (ct in 1..repeat) {
            val breakfastContent = "breakfast$ct"
            val breakfast = sharedPreferenceStorage.getInfo(breakfastContent)

            val lunchContent = "lunch$ct"
            val lunch = sharedPreferenceStorage.getInfo(lunchContent)

            val dinnerContent = "dinner$ct"
            val dinner = sharedPreferenceStorage.getInfo(dinnerContent)

            val gradeMeal = GradeMealData(breakfast, lunch, dinner)
            gradeMeals.value?.put(ct, gradeMeal)
        }
    }

    fun saveMeal(grade: Int, type: String, time: String) {
        val content = "$type$grade"
        sharedPreferenceStorage.saveInfo(time, content)
    }

    private fun saveMeals() {
        for (ct in 1..repeat) {
            val breakfastContent = "breakfast$ct"
            sharedPreferenceStorage.saveInfo(
                gradeMeals.value!![ct]?.breakfast ?: "",
                breakfastContent
            )

            val lunchContent = "lunch$ct"
            sharedPreferenceStorage.saveInfo(gradeMeals.value!![ct]?.lunch ?: "", lunchContent)

            val dinnerContent = "dinner$ct"
            sharedPreferenceStorage.saveInfo(gradeMeals.value!![ct]?.dinner ?: "", dinnerContent)
        }
        _back.value = true
    }

    private fun sendMealData() {
        val accessToken = sharedPreferenceStorage.getInfo("access_token")
        var error = false
        for (grade in 1..repeat) {
            val meal = gradeMeals.value?.get(grade)
            val request = SetMealTimeRequest(
                grade,
                meal?.breakfast ?: "00:00:00",
                meal?.lunch ?: "00:00:00",
                meal?.dinner ?: "00:00:00"
            )
            settingApiImpl.setMealTime(accessToken, request).subscribe { response ->
                if (response.code() != 204) {
                    error = true
                }

            }
        }
        if (error) {
            _toastMessage.value = "오류가 발생하였습니다"
        } else {
            _toastMessage.value = "업데이트 하였습니다"
        }
        saveMeals()
    }

    fun onDoneSetting() {
        sendMealData()
    }

    fun editGrade(grade: Int) {
        editGrade.value = grade
    }

    fun backPressed() {
        _back.value = true
    }
}