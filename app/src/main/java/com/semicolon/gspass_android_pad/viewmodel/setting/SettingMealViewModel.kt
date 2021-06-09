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

    private val _gradeNumber = MutableLiveData<Int>(3)
    val gradeNumber : LiveData<Int> get() = _gradeNumber



    fun loadSchoolType(){
        val schoolName = sharedPreferenceStorage.getInfo("school_name")
        if(schoolName.contains("초등학교")){
            _gradeNumber.value = 6
        }
    }
}