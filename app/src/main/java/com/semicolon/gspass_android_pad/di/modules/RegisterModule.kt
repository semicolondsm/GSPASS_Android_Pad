package com.semicolon.gspass_android_pad.di.modules

import com.semicolon.gspass_android_pad.viewmodel.login.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val registerModule = module {
    viewModel { RegisterViewModel(get(),get()) }
}