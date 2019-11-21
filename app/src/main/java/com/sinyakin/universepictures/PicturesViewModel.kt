package com.sinyakin.universepictures

import androidx.lifecycle.*
import com.sinyakin.universepictures.di.DaggerPicturesViewModelComponent
import com.sinyakin.universepictures.di.PicturesViewModelComponent
import com.sinyakin.universepictures.di.PicturesViewModelModule
import com.sinyakin.universepictures.picturesList.PagedList
import com.sinyakin.universepictures.picturesList.PicturesPagedListAdapter
import com.sinyakin.universepictures.repository.Repository
import javax.inject.Inject

class PicturesViewModel : ViewModel() {

    @Inject
    lateinit var picturesPagedListAdapter: PicturesPagedListAdapter
    @Inject
    lateinit var pagedList: PagedList
    @Inject
    lateinit var repository: Repository

    val adapter = MutableLiveData<PicturesPagedListAdapter>()
    val clickPicture = MutableLiveData<PictureData>()
    private var viewModelComponent: PicturesViewModelComponent?
    private val exceptionLiveData: LiveData<Exception>
    private var exceptionObserver: Observer<Exception>
    val errors = MutableLiveData<Exception>()

    init {
        viewModelComponent = getPictureViewModelComponent()
        viewModelComponent?.inject(this)
        exceptionObserver = Observer {
            errors.value = it
        }
        exceptionLiveData = repository.getErrorStream()
        exceptionLiveData.observeForever(exceptionObserver)

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
        exceptionLiveData.removeObserver(exceptionObserver)
    }
}