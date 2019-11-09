package com.sinyakin.universepictures.repository

import com.sinyakin.universepictures.PictureData
import com.sinyakin.universepictures.network.ApodApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkRepository @Inject constructor(private val apodApi: ApodApi) :
    Repository {
    override suspend fun getPictures(starDate: String, endDate: String): List<PictureData> {
        val mediaData = apodApi.getMediaData(starDate, endDate).asReversed()
        return mediaData.filter { it.media_type == IMAGE }
    }

    companion object {
        const val IMAGE = "image"
    }
}