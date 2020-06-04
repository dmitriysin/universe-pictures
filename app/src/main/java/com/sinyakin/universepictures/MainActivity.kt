package com.sinyakin.universepictures

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.sinyakin.universepictures.baseui.BaseActivity
import com.sinyakin.universepictures.extensions.observe

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val viewModel = ViewModelProvider(this).get(PicturesViewModel::class.java)

        viewModel.observeRepositoryExceptions()

        if (isFirstLaunch(savedInstanceState)) {
            addFragment(PicturesListFragment())
        }

        observe(viewModel.onClickPicture) {
            if (it.isFirstTimeHandled()) {
                addFragment(PictureDetailsFragment())
            }
        }
    }
}