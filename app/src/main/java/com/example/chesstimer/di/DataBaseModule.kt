package com.example.chesstimer.di

import androidx.room.Room
import com.example.chesstimer.MainActivity
import com.example.chesstimer.dataBase.AppDataBase
import com.example.chesstimer.dataBase.DataBaseRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Provides
    @Singleton
    fun provideDataBaseRepo(dataBase : AppDataBase) : DataBaseRepo{
        return DataBaseRepo(dataBase)
    }

    @Provides
    @Singleton
    fun provideDataBase(activity: MainActivity) : AppDataBase {
        return Room.databaseBuilder(activity, AppDataBase::class.java, "database").build()
    }



}