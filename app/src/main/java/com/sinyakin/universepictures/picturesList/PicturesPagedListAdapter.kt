package com.sinyakin.universepictures.picturesList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.sinyakin.universepictures.PictureData
import com.sinyakin.universepictures.R
import com.sinyakin.universepictures.di.PicturesViewModelScope
import com.squareup.picasso.Picasso
import javax.inject.Inject


@PicturesViewModelScope
class PicturesPagedListAdapter @Inject constructor(
    diffUtilCallback: PicturesDiffUtilCallback,
    private val picasso: Picasso
) : PagedListAdapter<PictureData, PictureViewHolder>(diffUtilCallback) {

    var onItemClickListener:(pic:PictureData)->Unit={}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder =
        PictureViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.picture_list_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, picasso,onItemClickListener)
        }
    }
}