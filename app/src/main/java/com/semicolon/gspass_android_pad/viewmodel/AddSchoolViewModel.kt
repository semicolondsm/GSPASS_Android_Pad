package com.semicolon.gspass_android_pad.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.semicolon.gspass_android_pad.data.remote.login.LoginApiProvider
import com.semicolon.gspass_android_pad.model.GetSchoolResponse
import java.net.URLEncoder

class AddSchoolViewModel(private val loginApiProvider: LoginApiProvider) : ViewModel() {

    val schoolName = MutableLiveData<String>()

    private val _doneChooseSchool = MutableLiveData<Boolean>()
    val doneChooseSchool:LiveData<Boolean> get() = _doneChooseSchool

    private val _schools = MutableLiveData<List<GetSchoolResponse>>()
    val schools : LiveData<List<GetSchoolResponse>> get() = _schools

    fun loadSchools() {
        val encoder = URLEncoder.encode(schoolName.value,"utf-8")
        Log.d("검색","$encoder")
        loginApiProvider.getSchools(encoder).subscribe({response->
            Log.d("검색","결과:${response.body()}")
            if(response.isSuccessful){
                _schools.value = response.body()
            }
        }, {
            Log.e("RxDogTag",it.toString())
        })
    }
}