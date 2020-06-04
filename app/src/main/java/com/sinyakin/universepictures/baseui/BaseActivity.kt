package com.sinyakin.universepictures.baseui

import android.annotation.SuppressLint
import android.os.Bundle
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
    fun isFirstLaunch(savedInstanceState: Bundle?)=savedInstanceState==null
}