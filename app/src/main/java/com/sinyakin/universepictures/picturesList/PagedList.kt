package com.sinyakin.universepictures.picturesList

import android.os.Handler
import android.os.Looper
import androidx.paging.PagedList
import com.sinyakin.universepictures.di.PicturesViewModelScope
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject

@PicturesViewModelScope
class PagedList @Inject constructor(private val picturesDataSource: PicturesDataSource) {

    fun getList() = PagedList.Builder(
        picturesDataSource,
        config
    ).setFetchExecutor(Executors.newSingleThreadExecutor()).setNotifyExecutor(MainThreadExecutor()).build()

    private val config =
        PagedList.Config.Builder().setPageSize(10).setEnablePlaceholders(false).build()

    internal inner class MainThreadExecutor : Executor {
        private val mHandler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable) {
            mHandler.post(command)
        }
    }
}