package com.sinyakin.universepictures.repository

import com.sinyakin.universepictures.PictureData


interface Repository {
    suspend fun getPictures(starDate: String, endDate: String): List<PictureData>
}