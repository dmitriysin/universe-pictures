package com.sinyakin.universepictures.di

import com.sinyakin.universepictures.DateManager
import com.sinyakin.universepictures.picturesList.PicturesDiffUtilCallback
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope

@Module
class PicturesViewModelModule(private val viewModelScope: CoroutineScope) {

    @Provides
    @PicturesViewModelScope
    fun getDiffUtilCallback(): PicturesDiffUtilCallback = PicturesDiffUtilCallback()

    @Provides
    @PicturesViewModelScope
    fun getViewModelScope(): CoroutineScope = viewModelScope

    @Provides
    @PicturesViewModelScope
    fun getDateManger() = DateManager()
}

