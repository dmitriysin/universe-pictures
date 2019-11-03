package com.sinyakin.universepictures.picturesList

import androidx.recyclerview.widget.DiffUtil
import com.sinyakin.universepictures.PictureData

class PicturesDiffUtilCallback : DiffUtil.ItemCallback<PictureData>() {

    override fun areItemsTheSame(oldItem: PictureData, newItem: PictureData): Boolean =
        oldItem.date == newItem.date

    override fun areContentsTheSame(oldItem: PictureData, newItem: PictureData): Boolean =
        oldItem.url == newItem.url
}