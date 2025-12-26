package com.ashish.multiplateformapp.application

import android.app.Application
import com.ashish.multiplateformapp.di.initKoin
import org.koin.android.ext.koin.androidContext

class App : Application(){

    override fun onCreate() {
        super.onCreate()
        initKoin(
            config = {
                androidContext(this@App)
            }
        )
    }
}