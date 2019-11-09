package com.sinyakin.universepictures.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LifecycleOwner.observe(ld: LiveData<T>, func: (arg: T) -> Unit) {
    ld.observe(
        this, Observer {
            func(it)
        }
    )
}