package com.example.chesstimer.daggerModule

import com.example.chesstimer.dataBase.DataBaseRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Provides
    @Singleton
    fun provideDataBaseRepo() : DataBaseRepo{
        return DataBaseRepo()
    }

}