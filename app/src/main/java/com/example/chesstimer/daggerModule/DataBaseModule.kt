package com.example.chesstimer.daggerModule

import com.example.chesstimer.dataBase.DataBaseRepo
import dagger.Module
import dagger.Provides

@Module
class DataBaseModule {

    @Provides
    fun provideDataBaseRepo() : DataBaseRepo{
        return DataBaseRepo()
    }

}