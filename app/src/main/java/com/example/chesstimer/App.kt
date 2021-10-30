package com.example.chesstimer

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.chesstimer.common.PrefUtils
import com.example.chesstimer.dataBase.AppDataBase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        PrefUtils.init(this)
    }
}


