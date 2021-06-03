package com.semicolon.gspass_android_pad.di

import android.app.Application
import com.semicolon.gspass_android_pad.di.modules.loginModule
import com.semicolon.gspass_android_pad.di.modules.mainModule
import com.semicolon.gspass_android_pad.di.modules.registerModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GsPassApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@GsPassApplication)
            modules(
                listOf(
                    mainModule,
                    loginModule,
                    registerModule
                )
            )
        }
    }
}