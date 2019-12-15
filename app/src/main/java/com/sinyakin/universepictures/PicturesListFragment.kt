package com.sinyakin.universepictures

import android.os.Bundle
import android.view.View
import com.sinyakin.universepictures.baseui.BaseFragment
import com.sinyakin.universepictures.extensions.observe
import com.sinyakin.universepictures.network.ServerError
import kotlinx.android.synthetic.main.pictures_list.*

class PicturesListFragment : BaseFragment() {
    override fun layoutId() = R.layout.pictures_list

    lateinit var viewModel: PicturesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel()
        viewModel.initPicturesAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(viewModel.adapter) {
            recyclerView?.adapter = it
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