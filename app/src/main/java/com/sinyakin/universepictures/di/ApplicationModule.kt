package com.sinyakin.universepictures.di

import android.app.Application
import android.content.Context
import com.sinyakin.universepictures.network.ApodApi
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @Singleton
    fun getApi(): ApodApi = getRetrofit().create(ApodApi::class.java)

    @Provides
    @Singleton
    fun getPicasso(): Picasso = Picasso.Builder(getApplicationContext()).build()

    @Provides
    @Singleton
    fun getApplicationContext(): Context = application.applicationContext

    private fun getRetrofit() = Retrofit.Builder().baseUrl("https://api.nasa.gov/planetary/")
        .addConverterFactory(GsonConverterFactory.create()).build()

}