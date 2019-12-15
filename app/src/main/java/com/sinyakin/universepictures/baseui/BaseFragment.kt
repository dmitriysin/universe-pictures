package com.sinyakin.universepictures.baseui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.sinyakin.universepictures.App
import com.sinyakin.universepictures.R
import kotlin.reflect.KClass

abstract class BaseFragment : Fragment() {

    abstract fun layoutId(): Int

    inline fun <reified T : ViewModel> getViewModel() =
        ViewModelProviders.of(requireActivity()).get(T::class.java)

    val appComponent = App.instance?.daggerApplicationComponent

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId(), container, false)
    }

    fun notifyUser(text: String, action: (() -> Unit)? = null) {
        view?.let { view ->
            val snackBar =
                Snackbar.make(view, text, Snackbar.LENGTH_INDEFINITE)
            action?.let {
                snackBar.setAction(getString(R.string.refresh)) {
                    action()
                }
            }
            context?.let { context ->
                snackBar.setActionTextColor(ContextCompat.getColor(context, R.color.colorWhite))
            }
            snackBar.show()
        }
    }

}