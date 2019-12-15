package com.sinyakin.universepictures

import androidx.lifecycle.viewModelScope
import com.sinyakin.universepictures.di.DaggerPicturesViewModelComponent
import com.sinyakin.universepictures.di.PicturesViewModelModule

class PicturesViewModelComponentInjector {

    fun inject(vm: PicturesViewModel) {
        DaggerPicturesViewModelComponent.builder()
            .picturesViewModelModule(PicturesViewModelModule(vm.viewModelScope))
            .applicationComponent(App.instance?.daggerApplicationComponent).build().inject(vm)
    }
}