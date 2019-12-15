package com.sinyakin.universepictures

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sinyakin.universepictures.network.ApodApi
import com.sinyakin.universepictures.network.ServerError
import com.sinyakin.universepictures.repository.NetworkRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.spy

class NetworkRepositoryTest : UnitTest() {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var apodApi: ApodApi

    private lateinit var networkRepository: NetworkRepository

    private val picture1 = PictureData(
        date = "2019-11-12",
        explanation = "A Nearly Sideways Spiral Galaxy",
        hdurl = "https://apod.nasa.gov/apod/image/1911/NGC3717_Hubble_4079.jpg",
        media_type = "image",
        service_version = "v1",
        title = "NGC 3717: A Nearly Sideways Spiral Galaxy",
        url = "https://apod.nasa.gov/apod/image/1911/NGC3717_Hubble_1080.jpg"
    )
    private val picture2 = PictureData(
        date = "2019-11-13",
        explanation = "Mercury",
        hdurl = "https://apod.nasa.gov/apod/image/1911/MercurySolarTransit_200mmF10_610nm_11112019.jpg",
        media_type = "image",
        service_version = "v1",
        title = "Mercury in Silhouette",
        url = "https://apod.nasa.gov/apod/image/1911/MercurySolarTransit_200mmF10_610nm_11112019_1024.jpg"
    )

    var pictures = ArrayList<PictureData>()
    @Before
    fun setUp() {
        networkRepository = spy(NetworkRepository(apodApi))
    }

    @Test
    fun `should load pictures`() {
        pictures.add(picture1)
        pictures.add(picture2)
        given(runBlocking { apodApi.getMediaData("1", "2") }).willReturn(pictures)
        assertEquals(runBlocking { networkRepository.getPictures("1", "2") }, pictures.asReversed())
    }

    @Test
    fun `should post ServerError when load pictures was not successful`() {
        val serverError = ServerError()
        given(networkRepository.serverError).willReturn(serverError)
        given(runBlocking { apodApi.getMediaData("1", "2") }).willThrow(Exception())
        runBlocking { networkRepository.getPictures("1", "2") }
        networkRepository.errors.observeForever {
            assertEquals(it, serverError)
        }
    }
}