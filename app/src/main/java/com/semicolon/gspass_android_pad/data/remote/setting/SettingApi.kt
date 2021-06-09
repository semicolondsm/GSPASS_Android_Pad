package com.semicolon.gspass_android_pad.data.remote.setting

import com.semicolon.gspass_android_pad.model.SetApplyTimeRequest
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SettingApi {
    @POST("/teacher/school/time")
    fun setApplyTime(@Body request:SetApplyTimeRequest):Single<Response<Void>>
}