package com.sinyakin.universepictures

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class PictureData (
    val date: String,
val explanation: String,
val hdurl: String,
val media_type: String,
val service_version: String,
val title: String,
val url: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(date)
        parcel.writeString(explanation)
        parcel.writeString(hdurl)
        parcel.writeString(media_type)
        parcel.writeString(service_version)
        parcel.writeString(title)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PictureData> {
        override fun createFromParcel(parcel: Parcel): PictureData {
            return PictureData(parcel)
        }

        override fun newArray(size: Int): Array<PictureData?> {
            return arrayOfNulls(size)
        }
    }
}