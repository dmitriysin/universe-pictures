package com.sinyakin.universepictures.di

import com.sinyakin.universepictures.MainActivity
import com.sinyakin.universepictures.Repository
import com.sinyakin.universepictures.network.ApodApi
import com.squareup.picasso.Picasso
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ApplicationModule::class])
@Singleton
interface ApplicationComponent {
    fun getPicasso(): Picasso
    fun getApi(): ApodApi
    fun getRepository():Repository
}