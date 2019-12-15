package com.sinyakin.universepictures

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DateManagerTest {

    private val date = "2100-01-01"
    lateinit var dateManager: DateManager

    @Before
    fun init() {
        dateManager = DateManager()
    }

    @Test
    fun `should add some days to date`() {
        val result = dateManager.addDaysToDate(date, 1)
        assertEquals(result, "2100-01-02")
    }

    @Test
    fun `should take some days from date`() {
        val result = dateManager.addDaysToDate(date, -1)
        assertEquals(result, "2099-12-31")
    }
}