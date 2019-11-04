package com.sinyakin.universepictures

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class PictureDetailFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.picture_detail_fragment, container, false)
        val pictureData = arguments?.getParcelable("PictureData") as PictureData
        Log.e("pictureData", pictureData.toString())
        return view
    }

    companion object {
        fun newInstance(pictureData: PictureData) =
            PictureDetailFragment().apply {
                arguments = Bundle().apply { putParcelable("PictureData", pictureData) }
            }

    }
}