package com.sinyakin.universepictures

import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21], application = App::class, manifest = Config.NONE)
open class AndroidTest : UnitTest()