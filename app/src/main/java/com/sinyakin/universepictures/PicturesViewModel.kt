package com.sinyakin.universepictures

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sinyakin.universepictures.di.DaggerPicturesViewModelComponent
import com.sinyakin.universepictures.di.PicturesViewModelComponent
import com.sinyakin.universepictures.di.PicturesViewModelModule
import com.sinyakin.universepictures.picturesList.PagedList
import com.sinyakin.universepictures.picturesList.PicturesPagedListAdapter
import javax.inject.Inject

class PicturesViewModel : ViewModel() {

    @Inject
    lateinit var picturesPagedListAdapter: PicturesPagedListAdapter
    @Inject
    lateinit var pagedList: PagedList

    val adapter = MutableLiveData<PicturesPagedListAdapter>()
    val clickPicture = MutableLiveData<PictureData>()
    private var viewModelComponent: PicturesViewModelComponent?

    init {
        viewModelComponent = getPictureViewModelComponent()
        viewModelComponent?.inject(this)
    }

    fun loadPictures() {
        adapter.value = picturesPagedListAdapter.apply {
            submitList(pagedList.getList())
            onItemClickListener = {
                clickPicture.value = it
            }
        }
    }

    private fun getPictureViewModelComponent() = DaggerPicturesViewModelComponent.builder()
        .picturesViewModelModule(PicturesViewModelModule(viewModelScope))
        .applicationComponent(App.instance.daggerApplicationComponent).build()

    override fun onCleared() {
        super.onCleared()
        viewModelComponent = null
    }
}