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

    val doneSetting = MutableLiveData(false)

    val duringTime = MutableLiveData<String>()
}