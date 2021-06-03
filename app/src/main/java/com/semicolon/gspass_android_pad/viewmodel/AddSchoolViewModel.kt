package com.semicolon.gspass_android_pad.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.semicolon.gspass_android_pad.data.remote.login.LoginApiProvider
import com.semicolon.gspass_android_pad.model.GetSchoolResponse

class AddSchoolViewModel(private val loginApiProvider: LoginApiProvider) : ViewModel() {

    val schoolName = MutableLiveData<String>()

    private val _schools = MutableLiveData<List<GetSchoolResponse>>()
    val schools : LiveData<List<GetSchoolResponse>> get() = _schools

    fun loadSchools() {
        loginApiProvider.getSchools(schoolName.value!!).subscribe({response->
            if(response.isSuccessful){
                _schools.value = response.body()
            }
        }, {
            Log.e("RxDogTag",it.toString())
        })
    }
}