package com.sinyakin.universepictures.picturesList

import androidx.paging.PositionalDataSource
import com.sinyakin.universepictures.DateManager
import com.sinyakin.universepictures.PictureData
import com.sinyakin.universepictures.repository.Repository
import com.sinyakin.universepictures.di.PicturesViewModelScope
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*
import javax.inject.Inject

@PicturesViewModelScope
open class PicturesDataSource @Inject constructor(
    private val viewModelCoroutineScope: CoroutineScope,
    private val repository: Repository,
    private val dateManager: DateManager
) : PositionalDataSource<PictureData>() {

    private var endDate = dateManager.getCurrentDate()
    private lateinit var loadParams: LoadParams

    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<PictureData>
    ) {
        viewModelCoroutineScope.launch {
            val pictures = loadPictures(params.pageSize)
            pictures?.let {
                callback.onResult(pictures, START_FROM_FIRST_POSITION)
            }
        }
        loadParams = LoadParams.Initial(params, callback)
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<PictureData>) {
        viewModelCoroutineScope.launch {
            val pictures = loadPictures(params.loadSize)
            pictures?.let {
                callback.onResult(pictures)
            }
        }
        loadParams = LoadParams.Range(params, callback)
    }

    private suspend fun loadPictures(loadSize: Int): List<PictureData>? =
        withContext(Dispatchers.IO) {
            val startDate = dateManager.addDaysToDate(endDate, -loadSize)
            val pictures = repository.getPictures(startDate, endDate)
            pictures?.let {
                val lastLoadedDate = pictures[pictures.lastIndex].date
                endDate = dateManager.addDaysToDate(lastLoadedDate, -1)
            }
            return@withContext pictures
        }

    fun retryLoadPictures() {
        when (loadParams) {
            is LoadParams.Initial -> with(loadParams as LoadParams.Initial) {
                loadInitial(initialParams, initialCallback)
            }
            is LoadParams.Range -> with(loadParams as LoadParams.Range) {
                loadRange(params, callback)
            }
        }
    }

    companion object {
        const val START_FROM_FIRST_POSITION = 0
    }
}