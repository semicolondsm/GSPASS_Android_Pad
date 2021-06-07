package com.semicolon.gspass_android_pad.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.semicolon.gspass_android_pad.data.local.SharedPreferenceStorage
import com.semicolon.gspass_android_pad.data.remote.login.LoginApiProvider

class MainViewModel(
    private val sharedPreferenceStorage: SharedPreferenceStorage,
    private val loginApiProvider: LoginApiProvider
) : ViewModel() {
    val needToLogin = MutableLiveData<Boolean>()

    private val _doneToken = MutableLiveData<Boolean>(false)
    val doneToken: LiveData<Boolean> get() = _doneToken

    fun checkLogin() {
        val refreshToken = sharedPreferenceStorage.getInfo("refresh_token")

        if (refreshToken.isNotBlank()) {
            doRefresh(refreshToken)
        } else {
            needToLogin.value = true
        }
    }

    private fun doRefresh(token: String) {
        loginApiProvider.refreshApi(token).subscribe { response ->
            when (response.code()) {
                200 -> {
                    sharedPreferenceStorage.saveInfo(response.body()!!.refreshToken,"refresh_token")
                    sharedPreferenceStorage.saveInfo(response.body()!!.accessToken, "access_token")
                }
                else -> {
                    needToLogin.value = true
                }
            }
            _doneToken.value = true
        }
    }

}