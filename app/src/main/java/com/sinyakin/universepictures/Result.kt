package com.sinyakin.universepictures

sealed class Result {

    data class Success(val res:List<PictureData>):Result()
    data class Error(val error:Exception):Result()

}