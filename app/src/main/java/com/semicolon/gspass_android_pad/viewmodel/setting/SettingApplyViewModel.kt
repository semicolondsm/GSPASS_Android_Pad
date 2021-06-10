package com.semicolon.gspass_android_pad.viewmodel.setting

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.semicolon.gspass_android_pad.data.local.SharedPreferenceStorage
import com.semicolon.gspass_android_pad.data.remote.setting.SettingApiImpl
import com.semicolon.gspass_android_pad.model.SetApplyTimeRequest

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

    val breakFastTimeView = MutableLiveData<String>()
    val launchTimeView = MutableLiveData<String>()
    val dinnerTimeView = MutableLiveData<String>()

    val doneSetting = MutableLiveData(false)

    val duringTime = MutableLiveData<String>()

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> get() = _toastMessage

    val breakFastEdit = MutableLiveData(false)
    val launchEdit = MutableLiveData(false)
    val dinnerEdit = MutableLiveData(false)

    private val _back = MutableLiveData(false)
    val back: LiveData<Boolean> get() = _back

    fun loadSettings() {
        breakFastChecked.value = sharedPreferenceStorage.getInfo("break_fast_check", false)
        launchChecked.value = sharedPreferenceStorage.getInfo("launch_check", false)
        dinnerChecked.value = sharedPreferenceStorage.getInfo("dinner_check", false)

        breakFastTime.value = sharedPreferenceStorage.getInfo("break_fast_time")
        launchTime.value = sharedPreferenceStorage.getInfo("lunch_time")
        dinnerTime.value = sharedPreferenceStorage.getInfo("dinner_time")

        breakFastTimeView.value = sharedPreferenceStorage.getInfo("break_fast_view")
        launchTimeView.value = sharedPreferenceStorage.getInfo("launch_view")
        dinnerTimeView.value = sharedPreferenceStorage.getInfo("dinner_view")

    }

    fun doneSetting() {
        if (doneSetting.value!!) {
            sendSetting()
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun sendSetting() {
        val breakFast:String = if (breakFastTime.value == "") {
            "00:00:00"
        } else {
            breakFastTime.value!!
        }
        val launch:String = if(launchTime.value == ""){
            "00:00:00"
        }else{
            launchTime.value!!
        }
        val dinner:String = if(dinnerTime.value==""){
            "00:00:00"
        }else{
            dinnerTime.value!!
        }

        val accessToken = sharedPreferenceStorage.getInfo("access_token")
        settingApiImpl.setApplyTime(
            accessToken,
            SetApplyTimeRequest(
                breakFast,
                launch,
                dinner,
                duringTime.value!!.toInt()
            )
        ).subscribe { response ->
            if (response.code() == 204) {
                sharedPreferenceStorage.saveInfo(breakFastChecked.value!!, "break_fast_check")
                sharedPreferenceStorage.saveInfo(launchChecked.value!!, "launch_check")
                sharedPreferenceStorage.saveInfo(dinnerChecked.value!!, "dinner_check")

                sharedPreferenceStorage.saveInfo(breakFastTime.value ?: "", "break_fast_time")
                sharedPreferenceStorage.saveInfo(launchTime.value ?: "", "lunch_time")
                sharedPreferenceStorage.saveInfo(dinnerTime.value ?: "", "dinner_time")

                sharedPreferenceStorage.saveInfo(breakFastTimeView.value ?: "", "break_fast_view")
                sharedPreferenceStorage.saveInfo(launchTimeView.value ?: "", "lunch_view")
                sharedPreferenceStorage.saveInfo(dinnerTimeView.value ?: "", "dinner_view")

                sharedPreferenceStorage.saveInfo(duringTime.value!!,"during_time")

                _toastMessage.value = "업데이트 되었습니다"

                _back.value = true
            } else {
                _toastMessage.value = "설정을 업데이트하지 못하였습니다"
            }
        }
    }

    fun editBreakfast(){
        breakFastEdit.value = true
    }

    fun editLunch(){
        launchEdit.value = true
    }

    fun editDinner(){
        dinnerEdit.value = true
    }

    fun back() {
        _back.value = true
    }
}