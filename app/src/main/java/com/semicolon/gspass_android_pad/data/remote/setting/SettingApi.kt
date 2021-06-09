package com.semicolon.gspass_android_pad.data.remote.setting

import com.semicolon.gspass_android_pad.model.SetApplyTimeRequest
import com.semicolon.gspass_android_pad.model.SetMealTimeRequest
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface SettingApi {
    @POST("/teacher/school/time")
    fun setApplyTime(
        @Header("Authorization") token: String,
        @Body request: SetApplyTimeRequest
    ): Single<Response<Void>>

    @POST("/teacher/grade/time")
    fun setMealTime(
        @Header("Authorization") token: String,
        @Body request: SetMealTimeRequest
    ): Single<Response<Void>>
}