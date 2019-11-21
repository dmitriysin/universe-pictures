package com.sinyakin.universepictures.repository

import androidx.lifecycle.MutableLiveData
import com.sinyakin.universepictures.PictureData
import com.sinyakin.universepictures.Result
import com.sinyakin.universepictures.network.ApodApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkRepository @Inject constructor(private val apodApi: ApodApi) :
    Repository {

    var errors = MutableLiveData<Exception>()

    /*override suspend fun getPictures(starDate: String, endDate: String): List<PictureData> {
        val mediaData = apodApi.getMediaData(starDate, endDate).asReversed()
        return mediaData.filter { it.media_type == IMAGE }
    }*/

    override fun getErrorStream(): MutableLiveData<Exception> {
        return errors
    }

    override suspend fun getPictures(starDate: String, endDate: String): List<PictureData>? {
        try {
            val mediaData = apodApi.getMediaData(starDate, endDate).asReversed()
            return mediaData.filter { it.media_type == IMAGE }
        } catch (e: Exception) {
            errors.postValue(e)
            e.printStackTrace()
            return null
        }
    }

    companion object {
        const val IMAGE = "image"
    }
}