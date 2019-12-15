package com.sinyakin.universepictures

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
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

    private var componentInjector: PicturesViewModelComponentInjector? = null

    val adapter = MutableLiveData<PicturesPagedListAdapter>()
    var clickedPicture: PictureData? = null
    val onClickPicture = MutableLiveData<Event<Unit>>()

    private var exceptionLiveData: LiveData<Exception>? = null
    private var exceptionObserver: Observer<Exception>? = null
    val errors = MutableLiveData<Exception>()

    init {
        componentInjector = PicturesViewModelComponentInjector()
        componentInjector?.inject(this)
    }



    fun initPicturesAdapter() {
        adapter.value = picturesPagedListAdapter.apply {
            val pl = pagedList.getList()
            submitList(pl)
            onItemClickListener = {
                clickedPicture = it
                onClickPicture.value = Event(Unit)
            }
        }
    }

    fun retry() {
        picturesDataSource.retryLoadPictures()
    }

    fun observeRepositoryExceptions() {
        exceptionObserver = Observer { exception ->
            when (exception) {
                is ServerError -> errors.value = exception
            }
        }
        exceptionLiveData = repository.getErrorStream()
        exceptionLiveData?.observeForever(exceptionObserver!!)
    }

    private fun unsubscribeFromRepositoryExceptions() {
        exceptionObserver?.let {
            exceptionLiveData?.removeObserver(it)
        }
    }

    override fun onCleared() {
        super.onCleared()
        componentInjector = null
        unsubscribeFromRepositoryExceptions()
    }

}