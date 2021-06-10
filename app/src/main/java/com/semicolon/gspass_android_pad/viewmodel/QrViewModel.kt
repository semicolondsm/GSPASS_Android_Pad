package com.semicolon.gspass_android_pad.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.semicolon.gspass_android_pad.data.remote.qrcode.QrCodeApiImpl

class QrViewModel(private val qrCodeApiImpl: QrCodeApiImpl) : ViewModel() {
    val token = MutableLiveData<String>()

    private val _qrToastMessage = MutableLiveData<String>()
    val qrToastMessage: LiveData<String> get() = _qrToastMessage

    private val _isOnTime = MutableLiveData(false)
    val isOnTime:LiveData<Boolean> get() = _isOnTime

    fun checkQr() {
        qrCodeApiImpl.checkQrCode(token.value!!).subscribe{response->
            if(response.code()==204){
                _qrToastMessage.value = "확인되었습니다"
                _isOnTime.value = true
            }
            else{
                _qrToastMessage.value = "아직 급식시간이 아닙니다!"
                _isOnTime.value = false
            }
        }
    }
}