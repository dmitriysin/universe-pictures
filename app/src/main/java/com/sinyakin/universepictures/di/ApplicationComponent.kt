package com.sinyakin.universepictures.di

import com.sinyakin.universepictures.MainActivity
import com.sinyakin.universepictures.network.ApodApi
import com.squareup.picasso.Picasso
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ApplicationModule::class])
@Singleton
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity)
    fun getPicasso(): Picasso
    fun getApi(): ApodApi
}