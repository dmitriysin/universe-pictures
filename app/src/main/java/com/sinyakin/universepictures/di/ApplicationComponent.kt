package com.sinyakin.universepictures.di

import com.sinyakin.universepictures.PictureDetailsFragment
import com.sinyakin.universepictures.repository.Repository
import com.sinyakin.universepictures.network.ApodApi
import com.squareup.picasso.Picasso
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ApplicationModule::class])
@Singleton
interface ApplicationComponent {
    fun getPicasso(): Picasso
    fun getApi(): ApodApi
    fun getRepository(): Repository
    fun inject(pictureDetailsFragment: PictureDetailsFragment)
}