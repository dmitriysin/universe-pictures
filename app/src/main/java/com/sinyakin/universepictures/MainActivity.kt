package com.sinyakin.universepictures

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.sinyakin.universepictures.baseui.BaseActivity
import com.sinyakin.universepictures.extensions.observe
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val viewModel = ViewModelProviders.of(this).get(PicturesViewModel::class.java)

        observe(viewModel.adapter) {
            recyclerView.adapter = it
        }

        viewModel.loadPictures()

        observe(viewModel.clickPicture) {
            addFragment(PictureDetailFragment())
        }
        observe(viewModel.errors) {
            Toast.makeText(this,"Exception",Toast.LENGTH_SHORT).show()
        }
    }
}