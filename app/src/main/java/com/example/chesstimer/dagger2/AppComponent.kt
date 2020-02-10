package com.example.chesstimer.dagger2

import com.example.chesstimer.timer.TimerFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NavModule::class , TimerModule::class])
@Singleton
interface AppComponent {
    fun injectTimerFragment(timerFragment: TimerFragment)
}