package com.example.chesstimer.basic

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import org.koin.core.scope.Scope
import org.koin.java.KoinJavaComponent

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity(){

    override fun onDestroy() {
        super.onDestroy()
    }


}