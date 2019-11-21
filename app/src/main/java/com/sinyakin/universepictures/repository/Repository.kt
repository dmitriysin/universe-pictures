package com.sinyakin.universepictures.repository

import androidx.lifecycle.MutableLiveData
import com.sinyakin.universepictures.PictureData


interface Repository {
    suspend fun getPictures(starDate: String, endDate: String): List<PictureData>?
    fun getErrorStream(): MutableLiveData<Exception>
}