package com.semicolon.gspass_android_pad.data.remote.login

import com.semicolon.gspass_android_pad.model.GetSchoolResponse
import com.semicolon.gspass_android_pad.model.LoginRequest
import com.semicolon.gspass_android_pad.model.RegisterRequest
import com.semicolon.gspass_android_pad.model.TokenResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginApi {
    @POST("/teacher/register")
    fun register(@Body request: RegisterRequest): Single<Response<TokenResponse>>

    @POST("/teacher/login")
    fun login(@Body request: LoginRequest): Single<Response<TokenResponse>>

    @POST("/refresh")
    fun refreshToken(@Header("X-Refresh-Token")token: String): Single<Response<TokenResponse>>

    @GET("/school")
    fun getSchools(@Query("name") name:String):Single<Response<ArrayList<GetSchoolResponse>>>
}