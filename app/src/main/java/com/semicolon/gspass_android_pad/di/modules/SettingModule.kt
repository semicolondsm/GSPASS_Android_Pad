package com.semicolon.gspass_android_pad.di.modules

import com.semicolon.gspass_android_pad.viewmodel.SettingMealViewModel
import com.semicolon.gspass_android_pad.viewmodel.SettingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val settingModule = module {
    viewModel { SettingViewModel(get()) }

    viewModel { SettingMealViewModel(get()) }
}