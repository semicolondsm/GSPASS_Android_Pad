package com.semicolon.gspass_android_pad.di.modules

import com.semicolon.gspass_android_pad.adapter.GetSchoolsAdapter
import com.semicolon.gspass_android_pad.viewmodel.AddSchoolViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val addSchoolModule = module {
    viewModel { AddSchoolViewModel(get()) }
    single { GetSchoolsAdapter(get()) }
}