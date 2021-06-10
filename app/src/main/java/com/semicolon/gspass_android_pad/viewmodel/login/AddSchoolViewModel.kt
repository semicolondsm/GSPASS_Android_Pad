package com.semicolon.gspass_android_pad.viewmodel.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.semicolon.gspass_android_pad.data.local.SharedPreferenceStorage
import com.semicolon.gspass_android_pad.data.remote.login.LoginApiImpl
import com.semicolon.gspass_android_pad.model.GetSchoolResponse
import com.semicolon.gspass_android_pad.model.PostSchoolRequest
import java.net.URLEncoder

class AddSchoolViewModel(
    private val loginApiImpl: LoginApiImpl,
    private val sharedPreferenceStorage: SharedPreferenceStorage
) : ViewModel() {

    val schoolName = MutableLiveData<String>()

    val schools = MutableLiveData<ArrayList<GetSchoolResponse>>()

    private val _chooseSchool = MutableLiveData<GetSchoolResponse>()
    val chooseSchool: LiveData<GetSchoolResponse> get() = _chooseSchool

    private val _finishSchool = MutableLiveData(false)
    val finishSchool : LiveData<Boolean> get() = _finishSchool

    fun loadSchools(name: String) {
        val encoder = URLEncoder.encode(name, "utf-8")
        loginApiImpl.getSchools(encoder).subscribe { response ->
            if (response.isSuccessful) {
                schools.value = (response.body() as ArrayList<GetSchoolResponse>)
            }
        }
    }

    fun chooseSchool(model: GetSchoolResponse) {
        _chooseSchool.value = model
    }

    private fun saveSchoolInfo(model: GetSchoolResponse, randomCode: String) {
        sharedPreferenceStorage.saveInfo(model.scCode, "sc_code")
        sharedPreferenceStorage.saveInfo(model.schoolCode, "school_code")
        sharedPreferenceStorage.saveInfo(model.name, "school_name")
        sharedPreferenceStorage.saveInfo(randomCode, "random_code")
    }

    fun postSchool(model: GetSchoolResponse) {
        val request = PostSchoolRequest(model.schoolCode, model.scCode, model.name)
        loginApiImpl.postSchool(request).subscribe { response ->
            if (response.code() == 200) {
                saveSchoolInfo(model,response.body()!!.randomCode)
            }
        }
    }
}