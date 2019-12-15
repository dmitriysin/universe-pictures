package com.sinyakin.universepictures.network

class ServerError(private val explanation:String?=null) : Exception(explanation)