package com.sinyakin.universepictures

class Event<T>(private val content: T?) {

    private var alreadyReturned = false

    fun getData(): T? {
        return if (alreadyReturned) null
        else {
            alreadyReturned = true
            content
        }
    }
}