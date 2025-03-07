package com.nadafeteiha.fetchrewards

import android.app.Application
import com.nadafeteiha.fetchrewards.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App.applicationContext)
            modules(appModule)
        }
    }
}