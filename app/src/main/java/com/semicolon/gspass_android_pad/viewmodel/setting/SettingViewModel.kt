package com.semicolon.gspass_android_pad.viewmodel.setting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.semicolon.gspass_android_pad.data.local.SharedPreferenceStorage

class SettingViewModel(private val sharedPreferenceStorage: SharedPreferenceStorage) : ViewModel() {

    val startApplySetting = MutableLiveData(false)

    val startMealSetting = MutableLiveData(false)

    fun loadSettings(){
    }

    fun setApply(){
        startApplySetting.value = true
    }

    fun setMeal(){
        startMealSetting.value = true
    }

}