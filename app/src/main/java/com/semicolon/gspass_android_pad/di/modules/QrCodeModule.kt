package com.semicolon.gspass_android_pad.di.modules

import com.semicolon.gspass_android_pad.data.remote.qrcode.QrCodeApiImpl
import com.semicolon.gspass_android_pad.viewmodel.QrViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val qrCodeModule = module {
    single { QrCodeApiImpl() }
    viewModel { QrViewModel(get()) }
}