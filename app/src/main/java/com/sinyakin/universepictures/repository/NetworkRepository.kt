package com.sinyakin.universepictures.repository

import androidx.lifecycle.MutableLiveData
import com.sinyakin.universepictures.PictureData
import com.sinyakin.universepictures.network.ApodApi
import com.sinyakin.universepictures.network.ServerError
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkRepository @Inject constructor(
    private val apodApi: ApodApi
) : Repository {

    var errors = MutableLiveData<Exception>()
    val serverError by lazy { ServerError() }

    override fun getErrorStream(): MutableLiveData<Exception> {
        return errors
    }

    override suspend fun getPictures(starDate: String, endDate: String): List<PictureData>? {
        return try {
            val mediaData = apodApi.getMediaData(starDate, endDate).asReversed()
            val pictures = mediaData.filter { it.media_type == IMAGE }
            pictures
        } catch (e: Exception) {
            errors.postValue(serverError)
            e.printStackTrace()
            null
        }
    }

    companion object {
        const val IMAGE = "image"
    }
}