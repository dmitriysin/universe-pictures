package com.sinyakin.universepictures

import android.os.Bundle
import android.os.PersistableBundle
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

        if (isFirstLaunch(savedInstanceState)) {
            addFragment(PicturesListFragment())
        }

        observe(viewModel.onClickPicture) {
            if (it.isFirstTimeHandled()){
                addFragment(PictureDetailsFragment())
            }
        }
    }
}