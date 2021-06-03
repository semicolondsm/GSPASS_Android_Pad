package com.semicolon.gspass_android_pad.di.modules

import com.semicolon.gspass_android_pad.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel { MainViewModel(get(),get()) }
}