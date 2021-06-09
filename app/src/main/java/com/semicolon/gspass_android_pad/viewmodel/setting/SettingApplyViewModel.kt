package com.semicolon.gspass_android_pad.viewmodel.setting

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.semicolon.gspass_android_pad.data.local.SharedPreferenceStorage
import com.semicolon.gspass_android_pad.data.remote.setting.SettingApiImpl
import com.semicolon.gspass_android_pad.model.SetApplyTimeRequest
import java.text.SimpleDateFormat
import java.util.*

class SettingApplyViewModel(
    private val sharedPreferenceStorage: SharedPreferenceStorage,
    private val settingApiImpl: SettingApiImpl
) : ViewModel() {
    val breakFastChecked = MutableLiveData(false)
    val launchChecked = MutableLiveData(false)
    val dinnerChecked = MutableLiveData(false)

    val breakFastTime = MutableLiveData<Date>()
    val launchTime = MutableLiveData<Date>()
    val dinnerTime = MutableLiveData<Date>()

    val breakFastTimeView = MutableLiveData<String>()
    val launchTimeView = MutableLiveData<String>()
    val dinnerTimeView = MutableLiveData<String>()

    val doneSetting = MutableLiveData(false)

    val duringTime = MutableLiveData<String>()

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage : LiveData<String> get() = _toastMessage

    val breakFastEdit = MutableLiveData(false)
    val launchEdit = MutableLiveData(false)
    val dinnerEdit = MutableLiveData(false)

    fun loadSettings() {
        breakFastChecked.value = sharedPreferenceStorage.getInfo("break_fast_check", false)
        launchChecked.value = sharedPreferenceStorage.getInfo("launch_check", false)
        dinnerChecked.value = sharedPreferenceStorage.getInfo("dinner_check", false)
    }

    fun doneSetting() {
        if (doneSetting.value!!) {
            sendSetting()
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun sendSetting() {
        val breakFast = try{
            SimpleDateFormat("HH:mm:00").format(breakFastTime.value)
        }catch (e:Exception){
            null
        }
        val launch = try {
            SimpleDateFormat("HH:mm:00").format(launchTime.value)
        }catch (e:Exception){
            null
        }
        val dinner = try {
            SimpleDateFormat("HH:mm:00").format(dinnerTime.value)
        }catch(e:Exception){
            null
        }
        settingApiImpl.setApplyTime(
            SetApplyTimeRequest(
                breakfast_period = breakFast,
                launch_period = launch,
                dinner_period = dinner,
                duringTime.value!!.toInt()
            )
        ).subscribe { response->
            if(response.code()==204){
                sharedPreferenceStorage.saveInfo(breakFastChecked.value!!, "break_fast_check")
                sharedPreferenceStorage.saveInfo(launchChecked.value!!, "launch_check")
                sharedPreferenceStorage.saveInfo(dinnerChecked.value!!, "dinner_check")
            }else{
                _toastMessage.value = "설정을 업데이트하지 못하였습니다"
            }
        }
    }
}