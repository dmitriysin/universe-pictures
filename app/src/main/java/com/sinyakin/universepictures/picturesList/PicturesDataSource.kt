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
class PicturesDataSource @Inject constructor(
    private val viewModelCoroutineScope: CoroutineScope,
    private val repository: Repository,
    private val dateManager: DateManager,
    private val picasso: Picasso
) : PositionalDataSource<PictureData>() {

    private var endDate = dateManager.getCurrentDate()

    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<PictureData>
    ) {
        viewModelCoroutineScope.launch {
            val pictures = loadPictures(params.pageSize)
            callback.onResult(pictures, START_FROM_FIRST_POSITION)
        }
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<PictureData>) {
        viewModelCoroutineScope.launch {
            val pictures = loadPictures(params.loadSize)
            callback.onResult(pictures)
        }
    }

    private suspend fun loadPictures(loadSize: Int): List<PictureData> =
        withContext(Dispatchers.IO) {
            val startDate = dateManager.addDaysToDate(endDate, -loadSize)
            val pictures = repository.getPictures(startDate, endDate)
            viewModelCoroutineScope.launch { cachePictures(pictures) }
            val lastLoadedDate = pictures[pictures.lastIndex].date
            endDate = dateManager.addDaysToDate(lastLoadedDate, -1)
            return@withContext pictures
        }

    private fun cachePictures(pictures: List<PictureData>) {
        pictures.forEach {
            picasso.load(it.url).fetch()
        }
    }

    companion object {
        const val START_FROM_FIRST_POSITION = 0
    }
}