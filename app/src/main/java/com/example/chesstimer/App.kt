package com.example.chesstimer

import android.app.Application
import com.example.chesstimer.common.PrefUtils

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        PrefUtils.init(this)
    }
}


