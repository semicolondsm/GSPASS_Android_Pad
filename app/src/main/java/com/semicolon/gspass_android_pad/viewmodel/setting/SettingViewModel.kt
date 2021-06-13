package com.semicolon.gspass_android_pad.viewmodel.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.semicolon.gspass_android_pad.data.local.SharedPreferenceStorage
import com.semicolon.gspass_android_pad.data.remote.setting.SettingApiImpl
import com.semicolon.gspass_android_pad.model.GradeMealData

class SettingViewModel(
    private val sharedPreferenceStorage: SharedPreferenceStorage,
    private val settingApiImpl: SettingApiImpl
) : ViewModel() {

    val startApplySetting = MutableLiveData(false)

    val startMealSetting = MutableLiveData(false)

    private val _breakFastBool = MutableLiveData(false)
    private val _lunchBool = MutableLiveData(false)
    private val _dinnerBool = MutableLiveData(false)
    val breakFastBool: LiveData<Boolean> get() = _breakFastBool
    val lunchBool: LiveData<Boolean> get() = _lunchBool
    val dinnerBool: LiveData<Boolean> get() = _dinnerBool

    private val _breakfastView = MutableLiveData<String>()
    private val _lunchView = MutableLiveData<String>()
    private val _dinnerView = MutableLiveData<String>()
    val breakfastView: LiveData<String> get() = _breakfastView
    val lunchView: LiveData<String> get() = _lunchView
    val dinnerView: LiveData<String> get() = _dinnerView

    private val _duringTime = MutableLiveData<String>()
    val duringTime: LiveData<String> get() = _duringTime

    private val _isElementSchool = MutableLiveData(false)
    val isElementSchool: LiveData<Boolean> get() = _isElementSchool

    private val _mealTimes = MutableLiveData<ArrayList<GradeMealData>>()
    val mealTimes: LiveData<ArrayList<GradeMealData>> get() = _mealTimes

    private val _schoolName = MutableLiveData<String>()
    val schoolName: LiveData<String> get() = _schoolName

    private val _finishSetting = MutableLiveData(false)
    val finishSetting: LiveData<Boolean> get() = _finishSetting

    fun loadSettings() {
        loadApplySetting()
    }

    private fun loadApplySetting() {
        _breakFastBool.value = sharedPreferenceStorage.getInfo("break_fast_check", false)
        _lunchBool.value = sharedPreferenceStorage.getInfo("launch_check", false)
        _dinnerBool.value = sharedPreferenceStorage.getInfo("dinner_check", false)

        _breakfastView.value = "아침신청: " + sharedPreferenceStorage.getInfo("break_fast_view")
        _lunchView.value = "점심신청: " + sharedPreferenceStorage.getInfo("lunch_view")
        _dinnerView.value = "저녁신청: " + sharedPreferenceStorage.getInfo("dinner_view")

        _duringTime.value = "신청시간: " + sharedPreferenceStorage.getInfo("during_time") + "분"

        _isElementSchool.value = sharedPreferenceStorage.getInfo("school_name").contains("초등학교")

        loadSchoolInfo()
        loadMealSetting()
    }

    private fun loadSchoolInfo() {
        val token = sharedPreferenceStorage.getInfo("access_token")
        settingApiImpl.getSchoolInfo(token).subscribe { response->
            if(response.code()==200){
                _schoolName.value = response.body()!!.school_name
            }
        }
    }

    private fun loadMealSetting() {
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
            _mealTimes.value?.set(ct, gradeMeal)
        }
    }


    fun setApply() {
        startApplySetting.value = true
    }

    fun setMeal() {
        startMealSetting.value = true
    }

    fun finishSetting() {
        _finishSetting.value = true
    }

}