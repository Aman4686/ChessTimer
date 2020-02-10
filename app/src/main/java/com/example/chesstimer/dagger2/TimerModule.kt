package com.example.chesstimer.dagger2

import com.example.chesstimer.common.navigation.TimerNavigation
import com.example.chesstimer.timer.TimerViewModel
import dagger.Module
import dagger.Provides
@Module
class TimerModule {

    @Provides
    fun provideTimerViewModel(timerNavigation : TimerNavigation) : TimerViewModel {
        return TimerViewModel(timerNavigation)
    }
}