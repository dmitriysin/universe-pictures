package com.sinyakin.universepictures

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sinyakin.universepictures.di.DaggerPicturesViewModelComponent
import com.sinyakin.universepictures.di.PicturesViewModelModule
import com.sinyakin.universepictures.picturesList.PagedList
import com.sinyakin.universepictures.picturesList.PicturesPagedListAdapter
import javax.inject.Inject

class PicturesViewModel : ViewModel() {

    @Inject lateinit var picturesPagedListAdapter: PicturesPagedListAdapter
    @Inject lateinit var pagedList: PagedList

    val adapter: MutableLiveData<PicturesPagedListAdapter> = MutableLiveData()

    init {
        DaggerPicturesViewModelComponent.builder()
            .picturesViewModelModule(PicturesViewModelModule(viewModelScope))
            .applicationComponent(App.instance.daggerApplicationComponent).build().inject(this)
    }

    fun loadPictures() {
        adapter.value = picturesPagedListAdapter.apply { submitList(pagedList.getList()) }
    }
}