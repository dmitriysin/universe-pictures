package com.sinyakin.universepictures

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.spy

class EventTest : UnitTest() {

    lateinit var event: Event<String>

    @Before
    fun setUp() {
        event = spy(Event("content"))
    }

    @Test
    fun `should return false if already was called`() {
        assertEquals(event.isFirstTimeHandled(), true)
        assertEquals(event.isFirstTimeHandled(), false)
    }

    @Test
    fun `should return data only once`() {
        assertEquals(event.getData(), "content")
        assertEquals(event.getData(), null)
    }
}