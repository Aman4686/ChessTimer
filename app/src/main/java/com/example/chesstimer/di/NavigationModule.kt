package com.example.chesstimer.di

import com.example.chesstimer.MainActivity
import com.example.chesstimer.common.navigation.TimerNavigation
import com.example.chesstimer.common.navigation.TimerNavigator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NavigationModule() {

    @Provides
    @Singleton
    fun provideTimerNavigation(activity: MainActivity) : TimerNavigator {
        return TimerNavigation(activity)
    }
}