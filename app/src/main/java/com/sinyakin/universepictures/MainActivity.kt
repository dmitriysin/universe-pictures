package com.sinyakin.universepictures

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sinyakin.universepictures.network.ApodApi
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        recyclerView.layoutManager = GridLayoutManager(this, 2)

        val viewModel = ViewModelProviders.of(this).get(PicturesViewModel::class.java)
        viewModel.adapter.observe(this, Observer { picturesPagedListAdapter ->
            recyclerView.adapter = picturesPagedListAdapter
        })
        viewModel.loadPictures()
        viewModel.clickPicture.observe(this, Observer { pictureData ->
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, PictureDetailFragment.newInstance(pictureData))
                .addToBackStack(null)
                .commit()
        })
    }
}