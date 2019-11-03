package com.sinyakin.universepictures.picturesList

import androidx.paging.PositionalDataSource
import com.sinyakin.universepictures.PictureData
import com.sinyakin.universepictures.di.PicturesViewModelScope
import com.sinyakin.universepictures.network.ApodApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@PicturesViewModelScope
class PicturesDataSource @Inject constructor(
    private val viewModelCoroutineScope: CoroutineScope,
    private val apodApi: ApodApi
) :
    PositionalDataSource<PictureData>() {

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<PictureData>) {
        viewModelCoroutineScope.launch {
            val pictures = apodApi.getPicturesRange("2019-10-30")
            callback.onResult(pictures)
        }
    }

    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<PictureData>
    ) {
        viewModelCoroutineScope.launch {
            val pictures = apodApi.getPicturesRange("2019-10-30")
            callback.onResult(pictures, 0)
        }
    }
}