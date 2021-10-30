package com.example.chesstimer.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.chesstimer.dataBase.dao.SettingDAO
import com.example.chesstimer.dataBase.dao.SettingEntity

@Database(entities = [SettingEntity::class], version = 1 , exportSchema = false)
abstract class AppDataBase : RoomDatabase(){
    abstract fun settingDAO() : SettingDAO
}