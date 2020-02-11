package com.example.chesstimer

import com.example.chesstimer.module.NavModule
import com.example.chesstimer.module.TimerModule
import com.example.chesstimer.timer.TimerFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NavModule::class , TimerModule::class])
@Singleton
interface AppComponent {
    fun injectTimerFragment(timerFragment: TimerFragment)
}