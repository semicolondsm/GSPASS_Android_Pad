package com.semicolon.gspass_android_pad.viewmodel.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.semicolon.gspass_android_pad.data.local.SharedPreferenceStorage

class SettingViewModel(private val sharedPreferenceStorage: SharedPreferenceStorage) : ViewModel() {

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
    val breakfastView : LiveData<String> get() = _breakfastView
    val lunchView : LiveData<String> get() = _lunchView
    val dinnerView : LiveData<String> get() = _dinnerView

    private val _duringTime = MutableLiveData<String>()
    val duringTime : LiveData<String> get() = _duringTime

    private val _isElementSchool = MutableLiveData(false)
    val isElementSchool : LiveData<Boolean> get() = _isElementSchool

    fun loadSettings() {
        loadMealSetting()
        loadApplySetting()
    }

    private fun loadMealSetting() {

    }

    private fun loadApplySetting() {
        _breakFastBool.value = sharedPreferenceStorage.getInfo("break_fast_check", false)
        _lunchBool.value = sharedPreferenceStorage.getInfo("launch_check", false)
        _dinnerBool.value = sharedPreferenceStorage.getInfo("dinner_check", false)

        _breakfastView.value = sharedPreferenceStorage.getInfo("break_fast_view")
        _lunchView.value = sharedPreferenceStorage.getInfo("launch_view")
        _dinnerView.value = sharedPreferenceStorage.getInfo("dinner_view")

        _duringTime.value = sharedPreferenceStorage.getInfo("during_time")

        _isElementSchool.value = sharedPreferenceStorage.getInfo("school_name").contains("초등학교")
    }

    fun setApply() {
        startApplySetting.value = true
    }

    fun setMeal() {
        startMealSetting.value = true
    }

}