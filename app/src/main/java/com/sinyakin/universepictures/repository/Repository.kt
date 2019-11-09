package com.sinyakin.universepictures

import com.sinyakin.universepictures.network.ApodApi
import javax.inject.Inject
import javax.inject.Singleton


interface Repository {
    suspend fun getPictures(starDate: String, endDate: String): List<PictureData>

}