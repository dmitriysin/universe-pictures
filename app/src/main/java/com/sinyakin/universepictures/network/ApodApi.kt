package com.sinyakin.universepictures.network

import com.sinyakin.universepictures.PictureData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApodApi {

    @GET(ENDPOINT)
    suspend fun getPictureOfTheDay(@Query("date") date: String): PictureData

    @GET(ENDPOINT)
    suspend fun getPicturesRange(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String? = null
    ): ArrayList<PictureData>

    companion object {
        private const val API_KEY = "G7IZxpaKg1KdR21XFt06udX9c8A99dbcAIArco6I"
        const val ENDPOINT = "apod?api_key=$API_KEY"
    }
}