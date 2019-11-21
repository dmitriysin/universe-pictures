package com.sinyakin.universepictures.baseui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.sinyakin.universepictures.R

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }

    fun notifyUser(text: String, action: (() -> Unit)?=null) {
        val snackBar =
            Snackbar.make(findViewById(android.R.id.content), text, Snackbar.LENGTH_INDEFINITE)
        action?.let {
            snackBar.setAction(getString(R.string.refresh)) {
                action()
            }
        }
        snackBar.setActionTextColor(ContextCompat.getColor(this,R.color.colorWhite))
        snackBar.show()
    }


}