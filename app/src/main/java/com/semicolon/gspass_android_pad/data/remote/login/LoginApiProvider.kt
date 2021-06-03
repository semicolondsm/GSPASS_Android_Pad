package com.semicolon.gspass_android_pad.data.remote.login

import com.semicolon.gspass_android_pad.data.remote.ApiProvider
import com.semicolon.gspass_android_pad.model.GetSchoolResponse
import com.semicolon.gspass_android_pad.model.LoginRequest
import com.semicolon.gspass_android_pad.model.RegisterRequest
import com.semicolon.gspass_android_pad.model.TokenResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response
import java.net.URLEncoder

class LoginApiProvider {
    private fun provideLoginApi(): LoginApi =
        ApiProvider.retroFitBuilder.create(LoginApi::class.java)

    fun loginApi(request: LoginRequest): Single<Response<TokenResponse>> = provideLoginApi().login(request)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())

    fun registerApi(request: RegisterRequest): Single<Response<TokenResponse>> = provideLoginApi().register(request)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())

    fun getSchool(school: String): Single<Response<List<GetSchoolResponse>>> = provideLoginApi().getSchools(URLEncoder.encode(school,"utf-8"))
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
}