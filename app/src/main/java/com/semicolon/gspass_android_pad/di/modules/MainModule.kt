package com.semicolon.gspass_android_pad.di.modules

import com.semicolon.gspass_android_pad.data.local.SharedPreferenceStorage
import com.semicolon.gspass_android_pad.viewmodel.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single { SharedPreferenceStorage(androidApplication()) }

    viewModel { MainViewModel(get(),get()) }
}