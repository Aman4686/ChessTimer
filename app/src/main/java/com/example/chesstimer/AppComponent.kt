package com.example.chesstimer

import com.example.chesstimer.module.NavModule
import com.example.chesstimer.timer.TimerViewModel
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NavModule::class])
@Singleton
interface AppComponent {
    fun inject(timerViewModel: TimerViewModel)
}