package com.semicolon.gspass_android_pad.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.semicolon.gspass_android_pad.data.local.SharedPreferenceStorage
import com.semicolon.gspass_android_pad.data.remote.login.LoginApiProvider
import com.semicolon.gspass_android_pad.model.LoginRequest

class MainViewModel(
    private val sharedPreferenceStorage: SharedPreferenceStorage,
    private val loginApiProvider: LoginApiProvider
) : ViewModel() {
    val needToLogin = MutableLiveData<Boolean>()

    private val _doneToken = MutableLiveData<Boolean>(false)
    val doneToken: LiveData<Boolean> get() = _doneToken

    fun checkLogin() {
        val email = sharedPreferenceStorage.getInfo("user_email")
        val password = sharedPreferenceStorage.getInfo("user_password")

        if (email.isNotBlank() && password.isNotBlank()) {
            doLogin(email, password)
        } else {
            needToLogin.value = true
        }
    }

    private fun doLogin(email: String, password: String) {
        val request = LoginRequest(email, password)
        loginApiProvider.loginApi(request).subscribe { response ->
            when (response.code()) {
                200 -> {
                    sharedPreferenceStorage.saveInfo(response.body()!!.accessToken, "token")
                }
                else -> {
                    needToLogin.value = true
                }
            }
            _doneToken.value = true
        }
    }

}