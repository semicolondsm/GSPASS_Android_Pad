package com.semicolon.gspass_android_pad.data.remote.qrcode

import com.semicolon.gspass_android_pad.data.remote.ApiProvider
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response

class QrCodeApiImpl {
    private fun provideCheckApi(): QrCodeApi =
        ApiProvider.retroFitBuilder.create(QrCodeApi::class.java)

    fun checkQrCode(token: String): Single<Response<Void>> =
        provideCheckApi().checkPass(token)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
}