package com.sinyakin.universepictures.baseui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sinyakin.universepictures.R

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {
    fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }
}