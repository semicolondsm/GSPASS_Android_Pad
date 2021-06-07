package com.semicolon.gspass_android_pad.di.modules

import com.semicolon.gspass_android_pad.data.remote.login.LoginApiProvider
import com.semicolon.gspass_android_pad.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    single { LoginApiProvider() }

    viewModel { LoginViewModel(get(),get()) }
}