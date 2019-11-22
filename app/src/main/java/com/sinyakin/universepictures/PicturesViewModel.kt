package com.sinyakin.universepictures

import androidx.lifecycle.*
import com.sinyakin.universepictures.di.DaggerPicturesViewModelComponent
import com.sinyakin.universepictures.di.PicturesViewModelComponent
import com.sinyakin.universepictures.di.PicturesViewModelModule
import com.sinyakin.universepictures.network.ServerError
import com.sinyakin.universepictures.picturesList.PagedList
import com.sinyakin.universepictures.picturesList.PicturesDataSource
import com.sinyakin.universepictures.picturesList.PicturesPagedListAdapter
import com.sinyakin.universepictures.repository.Repository
import javax.inject.Inject

class PicturesViewModel : ViewModel() {

    @Inject
    lateinit var picturesPagedListAdapter: PicturesPagedListAdapter
    @Inject
    lateinit var pagedList: PagedList
    @Inject
    lateinit var picturesDataSource: PicturesDataSource
    @Inject
    lateinit var repository: Repository

    val adapter = MutableLiveData<PicturesPagedListAdapter>()
    var clickedPicture: PictureData?=null
    val onClickPicture=MutableLiveData<Event<Unit>>()

    private var viewModelComponent: PicturesViewModelComponent?
    private val exceptionLiveData: LiveData<Exception>
    private var exceptionObserver: Observer<Exception>
    val errors = MutableLiveData<Exception>()


    init {
        viewModelComponent = getPictureViewModelComponent()
        viewModelComponent?.inject(this)
        loadPictures()
        exceptionObserver = Observer { exception ->
            when (exception) {
                is ServerError -> errors.value = exception
            }
        }
        exceptionLiveData = repository.getErrorStream()
        exceptionLiveData.observeForever(exceptionObserver)

    }

    private fun loadPictures() {
        adapter.value = picturesPagedListAdapter.apply {
            submitList(pagedList.getList())
            onItemClickListener = {
                clickedPicture=it
                onClickPicture.value = Event(Unit)
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

    fun retry() {
        picturesDataSource.retryLoadPictures()
    }
}