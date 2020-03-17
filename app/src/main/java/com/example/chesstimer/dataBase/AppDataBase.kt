package com.example.chesstimer.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SettingEntity::class , TemporaryEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase(){
    abstract fun settingDAO() : SettingDAO
    abstract fun temporaryDAO() : TemporaryDAO
}