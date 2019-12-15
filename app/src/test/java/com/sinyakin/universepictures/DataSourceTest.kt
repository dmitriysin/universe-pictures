package com.sinyakin.universepictures

import androidx.paging.PositionalDataSource
import com.sinyakin.universepictures.picturesList.PicturesDataSource
import com.sinyakin.universepictures.repository.Repository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.Spy

class DataSourceTest : UnitTest() {

    @Mock
    private lateinit var repository: Repository
    @Mock
    private lateinit var dateManager: DateManager
    @Mock
    private lateinit var initialParams: PositionalDataSource.LoadInitialParams
    @Mock
    private lateinit var rangeParams: PositionalDataSource.LoadRangeParams
    @Spy
    private lateinit var initialCallback: PositionalDataSource.LoadInitialCallback<PictureData>
    @Mock
    private lateinit var rangeCallback: PositionalDataSource.LoadRangeCallback<PictureData>

    private lateinit var picturesDataSource: PicturesDataSource

    @Before
    fun setUp() {
        runBlocking {
            picturesDataSource = spy(PicturesDataSource(this, repository, dateManager))
        }
    }

    @Test
    fun `should save loadInitial params and retry load if needed`() {
        picturesDataSource.loadInitial(initialParams, initialCallback)
        picturesDataSource.retryLoadPictures()
        verify(picturesDataSource, times(2)).loadInitial(initialParams, initialCallback)
    }

    @Test
    fun `should save loadRange params and retry load if needed`() {
        picturesDataSource.loadRange(rangeParams, rangeCallback)
        picturesDataSource.retryLoadPictures()
        verify(picturesDataSource, times(2)).loadRange(rangeParams, rangeCallback)
    }
}