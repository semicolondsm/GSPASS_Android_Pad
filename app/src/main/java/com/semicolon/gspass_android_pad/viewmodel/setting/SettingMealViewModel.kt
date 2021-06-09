package com.semicolon.gspass_android_pad.viewmodel.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.semicolon.gspass_android_pad.data.local.SharedPreferenceStorage
import com.semicolon.gspass_android_pad.data.remote.setting.SettingApiImpl

class SettingMealViewModel(
    private val sharedPreferenceStorage: SharedPreferenceStorage,
    private val settingApiImpl: SettingApiImpl
) : ViewModel() {

    private val _isElementSchool = MutableLiveData(true)
    val isElementSchool : LiveData<Boolean> get() = _isElementSchool

    private val _back = MutableLiveData(false)
    val back : LiveData<Boolean> get() = _back

    private val _breakFastCheck = MutableLiveData(false)
    val breakFastCheck : LiveData<Boolean> get() = _breakFastCheck

    private val _lunchCheck = MutableLiveData(false)
    val lunchCheck : LiveData<Boolean> get() = _lunchCheck

    private val _dinnerCheck = MutableLiveData(false)
    val dinnerCheck : LiveData<Boolean> get() = _dinnerCheck

    fun loadSchoolType(){
        val schoolName = sharedPreferenceStorage.getInfo("school_name")
        _isElementSchool.value = schoolName.contains("초등학교")
        _breakFastCheck.value = sharedPreferenceStorage.getInfo("break_fast_check",false)
        _lunchCheck.value = sharedPreferenceStorage.getInfo("launch_check",false)
        _dinnerCheck.value = sharedPreferenceStorage.getInfo("dinner_check",false)
    }

    fun backPressed(){
        _back.value=true
    }
}