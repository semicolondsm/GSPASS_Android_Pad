package com.semicolon.gspass_android_pad.di.modules

import com.semicolon.gspass_android_pad.viewmodel.login.AddSchoolViewModel
import org.koin.dsl.module

val addSchoolModule = module {
    single { AddSchoolViewModel(get(),get()) }
}