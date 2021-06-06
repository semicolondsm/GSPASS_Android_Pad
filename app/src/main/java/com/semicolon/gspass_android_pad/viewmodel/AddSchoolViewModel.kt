package com.semicolon.gspass_android_pad.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.semicolon.gspass_android_pad.data.remote.login.LoginApiProvider
import com.semicolon.gspass_android_pad.model.GetSchoolResponse
import java.net.URLEncoder

class AddSchoolViewModel(private val loginApiProvider: LoginApiProvider) : ViewModel() {

    val schoolName = MutableLiveData<String>()

    val schools = MutableLiveData<ArrayList<GetSchoolResponse>>()

    val chooseSchool = MutableLiveData<GetSchoolResponse>(null)

    fun loadSchools() {
        val name = schoolName.value?.replace(" ", "")
        val encoder = URLEncoder.encode(name, "utf-8")
        loginApiProvider.getSchools(encoder).subscribe { response ->
            if (response.isSuccessful) {
                schools.value = (response.body() as ArrayList<GetSchoolResponse>)
            }
        }
    }

    fun chooseSchool(model: GetSchoolResponse) {
        chooseSchool.value = model
    }
}