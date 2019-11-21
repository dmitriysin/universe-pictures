package com.sinyakin.universepictures.picturesList

import androidx.paging.PositionalDataSource
import com.sinyakin.universepictures.PictureData

sealed class LoadParams {
    data class Initial(
        val initialParams: PositionalDataSource.LoadInitialParams,
        val initialCallback: PositionalDataSource.LoadInitialCallback<PictureData>
    ) : LoadParams()

    data class Range(
        val params: PositionalDataSource.LoadRangeParams,
        val callback: PositionalDataSource.LoadRangeCallback<PictureData>
    ) : LoadParams()
}