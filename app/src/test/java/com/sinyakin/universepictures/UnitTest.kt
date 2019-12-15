package com.sinyakin.universepictures

import org.junit.Rule
import org.junit.rules.TestRule
import org.mockito.MockitoAnnotations

open class UnitTest {
    @Rule
    @JvmField
    val testRule = TestRule { statement, _ ->
        MockitoAnnotations.initMocks(this)
        statement
    }
}