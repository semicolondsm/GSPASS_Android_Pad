package com.semicolon.gspass_android_pad.viewmodel.setting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.semicolon.gspass_android_pad.data.local.SharedPreferenceStorage
import com.semicolon.gspass_android_pad.data.remote.setting.SettingApiImpl

class SettingApplyViewModel(
    private val sharedPreferenceStorage: SharedPreferenceStorage,
    private val settingApiImpl: SettingApiImpl
) : ViewModel() {
    val breakFastChecked = MutableLiveData(false)
    val launchChecked = MutableLiveData(false)
    val dinnerChecked = MutableLiveData(false)

    val breakFastTime = MutableLiveData<String>()
    val launchTime = MutableLiveData<String>()
    val dinnerTime = MutableLiveData<String>()

    val doneSetting = MutableLiveData(false)

    val duringTime = MutableLiveData<String>()

    fun loadSettings(){
        breakFastChecked.value = sharedPreferenceStorage.getInfo("break_fast_check",false)
        launchChecked.value = sharedPreferenceStorage.getInfo("launch_check",false)
        dinnerChecked.value = sharedPreferenceStorage.getInfo("dinner_check",false)
    }

    fun doneSetting(){
        if(doneSetting.value!!){
            sharedPreferenceStorage.saveInfo(breakFastChecked.value!!,"break_fast_check")
            sharedPreferenceStorage.saveInfo(launchChecked.value!!,"launch_check")
            sharedPreferenceStorage.saveInfo(dinnerChecked.value!!,"dinner_check")
        }
    }
}