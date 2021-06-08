package com.semicolon.gspass_android_pad.data.remote.setting

import com.semicolon.gspass_android_pad.data.remote.ApiProvider
import com.semicolon.gspass_android_pad.model.SetApplyTimeRequest
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response

class SettingApiImpl {
    private fun provideSettingApi(): SettingApi =
        ApiProvider.retroFitBuilder.create(SettingApi::class.java)

    fun setApplyTime(request: SetApplyTimeRequest): Single<Response<Void>> =
        provideSettingApi().setApplyTime(request)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
}