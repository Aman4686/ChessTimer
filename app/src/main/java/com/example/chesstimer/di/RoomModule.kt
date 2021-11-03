package com.example.chesstimer.di

import android.content.Context
import androidx.room.Room
import com.example.chesstimer.AppDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {
    @Provides
    @Singleton
    //applicationContext required
    fun provideDataBase(appContext: Context) : AppDataBase {
        return Room.databaseBuilder(appContext, AppDataBase::class.java, "database").build()
    }
}