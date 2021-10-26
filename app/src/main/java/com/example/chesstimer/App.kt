package com.example.chesstimer

import android.app.Application
import androidx.room.Room
import com.example.chesstimer.common.PrefUtils
import com.example.chesstimer.dataBase.AppDataBase

class App : Application() {

    companion object{
        lateinit var instance : App
        lateinit var db : AppDataBase
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        PrefUtils.init(this)
        db = Room.databaseBuilder(applicationContext, AppDataBase::class.java, "database").build()
    }

}


