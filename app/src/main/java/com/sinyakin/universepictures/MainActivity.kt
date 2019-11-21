package com.sinyakin.universepictures

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.sinyakin.universepictures.baseui.BaseActivity
import com.sinyakin.universepictures.extensions.observe
import com.sinyakin.universepictures.network.ServerError
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val viewModel = ViewModelProviders.of(this).get(PicturesViewModel::class.java)

        observe(viewModel.adapter) {
            recyclerView.adapter = it
        }

        observe(viewModel.clickPicture) {
            addFragment(PictureDetailFragment())
        }

        observe(viewModel.errors) { exception ->
            when (exception) {
                is ServerError -> notifyUser(getString(R.string.server_error)) {
                    viewModel.retry()
                }
            }
        }
    }
}