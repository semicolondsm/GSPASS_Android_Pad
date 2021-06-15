package com.semicolon.gspass_android_pad.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.semicolon.gspass_android_pad.data.local.SharedPreferenceStorage
import com.semicolon.gspass_android_pad.data.remote.login.LoginApiImpl

class MainViewModel(
    private val sharedPreferenceStorage: SharedPreferenceStorage,
    private val loginApiImpl: LoginApiImpl
) : ViewModel() {
    val needToLogin = MutableLiveData(false)

    val needToGetSchool = MutableLiveData<Boolean>()

    val doneToken = MutableLiveData(false)

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage :LiveData<String> get() = _toastMessage

    fun checkSchool() {
        val school = sharedPreferenceStorage.getInfo("sc_code")
        if (school.isNotBlank()) {
            checkLogin()
        } else {
            needToGetSchool.value = true
        }
    }

    private fun checkLogin() {
        val refreshToken = sharedPreferenceStorage.getInfo("refresh_token")

        if (refreshToken.isNotBlank()) {
            doRefresh(refreshToken)
        } else {
            needToLogin.value = true
        }
    }

    private fun doRefresh(token: String) {
        loginApiImpl.refreshApi(token).subscribe { response ->
            when (response.code()) {
                200 -> {
                    sharedPreferenceStorage.saveInfo(
                        response.body()!!.refreshToken,
                        "refresh_token"
                    )
                    sharedPreferenceStorage.saveInfo(response.body()!!.accessToken, "access_token")
                    doneToken.value = true
                }
                else -> {
                    _toastMessage.value = "다시 로그인 해주세요"
                    needToLogin.value = true
                }
            }
        }
    }

}