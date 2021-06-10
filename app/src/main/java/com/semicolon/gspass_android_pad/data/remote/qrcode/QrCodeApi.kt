package com.semicolon.gspass_android_pad.data.remote.qrcode

import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.POST

interface QrCodeApi {
    @POST("/gspass")
    fun checkPass(@Header("Authorization")token:String):Single<Response<Void>>
}