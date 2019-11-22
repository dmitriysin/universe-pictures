package com.sinyakin.universepictures

import android.os.Bundle
import android.view.View
import com.sinyakin.universepictures.baseui.BaseFragment
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.picture_detail_fragment.*
import java.lang.Exception
import javax.inject.Inject

class PictureDetailsFragment : BaseFragment() {

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

        val picture = vm.clickPicture.value
        if (picture != null) {
            picasso.load(picture.getData()?.url).into(detailImageView, object : Callback {
                override fun onSuccess() {
                    detailExplanation.text = picture.getData()?.explanation
                }

                override fun onError(e: Exception?) {
                }
            })
        }
    }
}