package com.sinyakin.universepictures.picturesList

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sinyakin.universepictures.PictureData
import com.sinyakin.universepictures.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.picture_list_item.view.*

class PictureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(picture: PictureData, picasso: Picasso,onClickListener:(PictureData)->Unit) {
        picasso.load(picture.url).into(itemView.imageView)
        itemView.setOnClickListener { onClickListener(picture) }
    }
}