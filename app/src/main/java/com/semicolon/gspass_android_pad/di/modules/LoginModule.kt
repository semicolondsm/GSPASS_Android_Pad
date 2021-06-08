package com.semicolon.gspass_android_pad.di.modules

import com.semicolon.gspass_android_pad.data.remote.login.LoginApiImpl
import com.semicolon.gspass_android_pad.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    single { LoginApiImpl() }

    viewModel { LoginViewModel(get(),get()) }
}