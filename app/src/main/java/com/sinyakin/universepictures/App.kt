package com.sinyakin.universepictures

import android.app.Application
import com.sinyakin.universepictures.di.ApplicationComponent
import com.sinyakin.universepictures.di.DaggerApplicationComponent

open class App : Application() {

    lateinit var daggerApplicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        instance = this
        daggerApplicationComponent = DaggerApplicationComponent.builder().build()
    }

    companion object {
         var instance: App?=null
    }
}