package com.example.chesstimer.di

import com.example.chesstimer.AppDataBase
import com.example.chesstimer.dataBase.SettingRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SettingModule {

    @Provides
    @Singleton
    fun provideDataBaseRepo(dataBase : AppDataBase) : SettingRepo {
        return SettingRepo(dataBase)
    }

}