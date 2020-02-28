package com.example.chesstimer.basic

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity(){

    override fun onDestroy() {
        super.onDestroy()
    }


}