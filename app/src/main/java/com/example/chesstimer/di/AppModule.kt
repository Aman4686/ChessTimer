package com.example.chesstimer.di

import dagger.Module

@Module(includes = [NavigationModule::class , DataBaseModule::class])
class AppModule() {

}