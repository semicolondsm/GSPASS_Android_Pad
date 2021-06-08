package com.semicolon.gspass_android_pad.data.remote.login

import com.semicolon.gspass_android_pad.data.remote.ApiProvider
import com.semicolon.gspass_android_pad.model.*
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

    fun refreshApi(request: String): Single<Response<TokenResponse>> = provideLoginApi().refreshToken(request)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())

    fun getSchools(school: String): Single<Response<ArrayList<GetSchoolResponse>>> = provideLoginApi().getSchools(school)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())

    fun postSchool(request: PostSchoolRequest): Single<Response<PostSchoolResponse>> = provideLoginApi().postSchool(request)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
}