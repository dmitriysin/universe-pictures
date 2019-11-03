package com.sinyakin.universepictures.di

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
    fun viewModelScopeq(): CoroutineScope = viewModelScope
}

