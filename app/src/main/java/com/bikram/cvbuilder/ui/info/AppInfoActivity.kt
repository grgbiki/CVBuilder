package com.bikram.cvbuilder.ui.info

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bikram.cvbuilder.R

class AppInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_info)

        supportActionBar!!.title = "App Info"
    }
}