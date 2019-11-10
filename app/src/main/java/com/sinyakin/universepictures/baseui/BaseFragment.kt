package com.sinyakin.universepictures.baseui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.sinyakin.universepictures.App

abstract class BaseFragment : Fragment() {

    abstract fun layoutId(): Int

    inline fun <reified T : ViewModel> getViewModel() =
        ViewModelProviders.of(requireActivity()).get(T::class.java)

    val appComponent = App.instance.daggerApplicationComponent

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId(), container, false)
    }
}