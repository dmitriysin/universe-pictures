package com.sinyakin.universepictures

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.sinyakin.universepictures.network.ApodApi
import com.sinyakin.universepictures.network.ServerError
import com.sinyakin.universepictures.picturesList.PicturesDataSource
import com.sinyakin.universepictures.picturesList.PicturesPagedListAdapter
import com.sinyakin.universepictures.repository.NetworkRepository
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.robolectric.Robolectric
import org.robolectric.android.controller.ActivityController

class PicturesViewModelTest : AndroidTest() {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var picturesPagedListAdapterTest: PicturesPagedListAdapter

    @Mock
    private lateinit var apodApi: ApodApi

    @Mock
    private lateinit var picturesDataSourceTest: PicturesDataSource

    private lateinit var networkRepository: NetworkRepository

    private lateinit var picturesViewModel: PicturesViewModel

    private lateinit var activity: ActivityController<FragmentActivity>

    @Before
    fun setUp() {
        networkRepository = Mockito.spy(NetworkRepository(apodApi))
        activity = Robolectric.buildActivity(FragmentActivity::class.java).setup()
        picturesViewModel =
            ViewModelProvider(activity.get()).get(PicturesViewModel::class.java).apply {
                repository = networkRepository
                picturesPagedListAdapter = picturesPagedListAdapterTest
                picturesDataSource = picturesDataSourceTest
            }
    }

    @Test
    fun `should init pictures adapter`() {
        picturesViewModel.initPicturesAdapter()
        picturesViewModel.adapter.observeForever {
            assertEquals(picturesPagedListAdapterTest, it)
        }
    }

    @Test
    fun `should observe repository's exceptions `() {
        picturesViewModel.observeRepositoryExceptions()
        networkRepository.errors.value = ServerError("serverError")
        picturesViewModel.errors.observeForever {
            assertEquals("serverError", it.message)
        }
    }

    @Test
    fun `should unsubscribe from repository's exceptions when onCleared invoke`() {
        picturesViewModel.observeRepositoryExceptions()
        activity.destroy()
        networkRepository.errors.value = ServerError("serverError")
        picturesViewModel.errors.observeForever {
            assertEquals(null, it.message)
        }
    }

    @Test
    fun `should retry load pictures from pictures data source`() {
        picturesViewModel.retry()
        verify(picturesDataSourceTest).retryLoadPictures()
    }
}