package com.sinyakin.universepictures

import android.app.Application
import com.sinyakin.universepictures.di.ApplicationComponent
import com.sinyakin.universepictures.di.ApplicationModule
import com.sinyakin.universepictures.di.DaggerApplicationComponent

class App : Application() {

    lateinit var daggerApplicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        instance = this
        daggerApplicationComponent = DaggerApplicationComponent.
            builder().
            applicationModule(ApplicationModule(this)).
            build()
    }
    
    companion object {
        lateinit var instance: App
    }
}