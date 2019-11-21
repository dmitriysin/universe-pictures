package com.sinyakin.universepictures

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.sinyakin.universepictures.baseui.BaseFragment
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.picture_detail_fragment.*
import java.lang.Exception
import javax.inject.Inject

class PictureDetailFragment : BaseFragment() {

    override fun layoutId() = R.layout.picture_detail_fragment

    @Inject
    lateinit var picasso: Picasso

    private lateinit var vm: PicturesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        vm = getViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pic = vm.clickPicture.value
        picasso.load(pic?.url).into(detailImageView, object : Callback {
            override fun onSuccess() {
                detailExplanation.text = pic?.explanation
            }

            override fun onError(e: Exception?) {

            }
        })
    }
}