package com.sinyakin.universepictures.network

import com.sinyakin.universepictures.PictureData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApodApi {

    /**
     * start_date<end_date
     */
    @GET(ENDPOINT)
    suspend fun getMediaData(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String? = null
    ): List<PictureData>

    companion object {
        private const val API_KEY = "G7IZxpaKg1KdR21XFt06udX9c8A99dbcAIArco6I"
        const val ENDPOINT = "apod?api_key=$API_KEY"

        const val FULL_ADDRESS =
            "https://api.nasa.gov/planetary/apod?api_key=G7IZxpaKg1KdR21XFt06udX9c8A99dbcAIArco6I"
    }
}