package com.sinyakin.universepictures.di

import android.app.Application
import android.content.Context
import com.sinyakin.universepictures.App
import com.sinyakin.universepictures.network.ApodApi
import com.sinyakin.universepictures.repository.NetworkRepository
import com.sinyakin.universepictures.repository.Repository
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun getApi(): ApodApi = getRetrofit().create(ApodApi::class.java)

    @Provides
    @Singleton
    fun getPicasso(): Picasso =
        Picasso.Builder(getApplicationContext()).downloader(getOkHttpDownloader()).build().apply { setIndicatorsEnabled(true) }

    @Provides
    @Singleton
    fun getApplicationContext(): Context = getApplication().applicationContext


    @Provides
    @Singleton
    fun getRepository(dataSource: NetworkRepository): Repository = dataSource

    @Provides
    @Singleton
    fun getApplication():App= App.instance!!

    private fun getRetrofit(): Retrofit = Retrofit.Builder().baseUrl(APOD_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build()

    private fun getOkHttpDownloader(): OkHttp3Downloader {

        val ok = OkHttpClient.Builder().apply {
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
        }
        val cacheSize = 500_000_000L //500 Mb

        ok.cache(Cache(File(getApplicationContext().cacheDir, "file"), cacheSize))
        return OkHttp3Downloader(ok.build())
    }

    companion object {
        const val APOD_BASE_URL = "https://api.nasa.gov/planetary/"
    }
}

