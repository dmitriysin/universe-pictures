package com.sinyakin.universepictures

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.sinyakin.universepictures.network.ApodApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)

        val retrofit = Retrofit.Builder().baseUrl("https://api.nasa.gov/planetary/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        launch {
            val picture = retrofit.create(ApodApi::class.java).getPictureOfTheDay("2019-10-10")
            Log.e("pictures", picture.toString())

            val pictures = retrofit.create(ApodApi::class.java).getPicturesRange("2019-10-10")
            Log.e("pictures", pictures.toString())
        }

    }
}