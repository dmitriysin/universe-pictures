package com.sinyakin.universepictures

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sinyakin.universepictures.di.DaggerPicturesViewModelComponent
import com.sinyakin.universepictures.di.PicturesViewModelModule
import com.sinyakin.universepictures.picturesList.PagedList
import com.sinyakin.universepictures.picturesList.PicturesDataSource
import com.sinyakin.universepictures.picturesList.PicturesPagedListAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject

class PicturesViewModel : ViewModel() {

    @Inject
    lateinit var picturesPagedListAdapter: PicturesPagedListAdapter

    @Inject
    lateinit var pagedList: PagedList

    val adapter: MutableLiveData<PicturesPagedListAdapter> = MutableLiveData()

    init {
        val x = DaggerPicturesViewModelComponent.builder()
            .picturesViewModelModule(PicturesViewModelModule(viewModelScope))
            .applicationComponent(App.instance.daggerApplicationComponent).build()
        x.inject(this)
    }

    fun loadPictures() {
        val p = pagedList.getList()
        picturesPagedListAdapter.submitList(p)
        adapter.value = picturesPagedListAdapter
    }

}