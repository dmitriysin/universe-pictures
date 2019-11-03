package com.sinyakin.universepictures.di

import com.sinyakin.universepictures.PicturesViewModel
import com.sinyakin.universepictures.picturesList.PicturesPagedListAdapter
import dagger.Component

@PicturesViewModelScope
@Component(dependencies = [ApplicationComponent::class], modules = [PicturesViewModelModule::class])
interface PicturesViewModelComponent {
    fun inject(picturesPagedListAdapter: PicturesPagedListAdapter)
    fun inject(viewModel: PicturesViewModel)
}