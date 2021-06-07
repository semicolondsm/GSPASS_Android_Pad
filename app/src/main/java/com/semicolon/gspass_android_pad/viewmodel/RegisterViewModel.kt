package com.semicolon.gspass_android_pad.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.semicolon.gspass_android_pad.data.local.SharedPreferenceStorage
import com.semicolon.gspass_android_pad.data.remote.login.LoginApiProvider
import com.semicolon.gspass_android_pad.model.RegisterRequest

class RegisterViewModel(
    private val apiProvider: LoginApiProvider,
    private val sharedPreferenceStorage: SharedPreferenceStorage
) : ViewModel() {

    val userId = MutableLiveData<String>()
    val nEmptyName = MutableLiveData(false)

    val userPassword = MutableLiveData<String>()
    val nEmptyPassword = MutableLiveData(false)

    val userPasswordCheck = MutableLiveData<String>()
    val samePassword = MutableLiveData(false)

    val doneInput = MutableLiveData<Boolean>()

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> get() = _toastMessage

    private val _finishRegister = MutableLiveData(false)
    val finishRegister: LiveData<Boolean> get() = _finishRegister

    fun doRegister() {
        if (doneInput.value == true) {
            val schoolCode =sharedPreferenceStorage.getInfo("school_code")//여기에뭘 받아야 하는가
            val request =
                RegisterRequest(userId.value!!, userPassword.value!!, schoolCode)
            apiProvider.registerApi(request).subscribe({ subscribe ->
                when (subscribe.code()) {
                    200 -> {
                        _toastMessage.value = "회원가입에 성공하셨습니다"
                        _finishRegister.value = true
                    }
                    else->{
                        _toastMessage.value = "회원가입에 실패하였습니다"
                    }
                }
            }, {
                _toastMessage.value = "회원가입에 실패하였습니다"
            })

        } else {
            _toastMessage.value = "정보를 정확히 입력해주세요"
        }

    }

    fun finishRegister() {
        _finishRegister.value = true
    }


}