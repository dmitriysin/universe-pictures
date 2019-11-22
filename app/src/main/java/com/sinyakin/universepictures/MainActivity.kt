package com.sinyakin.universepictures

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.sinyakin.universepictures.baseui.BaseActivity
import com.sinyakin.universepictures.extensions.observe
import com.sinyakin.universepictures.network.ServerError
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val viewModel = ViewModelProviders.of(this).get(PicturesViewModel::class.java)

        addFragment(PicturesListFragment())

        observe(viewModel.clickPicture) {
            addFragment(PictureDetailsFragment())
        }
    }
}