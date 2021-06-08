package com.semicolon.gspass_android_pad.di.modules

import com.semicolon.gspass_android_pad.data.remote.setting.SettingApiImpl
import com.semicolon.gspass_android_pad.viewmodel.setting.SettingMealViewModel
import com.semicolon.gspass_android_pad.viewmodel.setting.SettingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val settingModule = module {
    single { SettingApiImpl() }

    viewModel { SettingViewModel(get()) }

    viewModel { SettingMealViewModel(get(),get()) }
}