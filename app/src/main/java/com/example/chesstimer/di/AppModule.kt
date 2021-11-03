package com.example.chesstimer.di

import dagger.Module

@Module(includes = [NavigationModule::class , SettingModule::class , RoomModule::class])
class AppModule()

