package com.example.chesstimer.dagger2

import androidx.appcompat.app.AppCompatActivity
import com.example.chesstimer.common.navigation.TimerNavigation
import com.example.chesstimer.common.navigation.TimerNavigator
import com.example.chesstimer.timer.TimerViewModel
import dagger.Module
import dagger.Provides

@Module
class NavModule(val activity: AppCompatActivity) {

    @Provides
    fun provideAppCompatActivity() : AppCompatActivity{
        return this.activity
    }

    @Provides
    fun provideTimerNavigation(activity: AppCompatActivity) : TimerNavigator {
        return TimerNavigation(activity)
    }
}