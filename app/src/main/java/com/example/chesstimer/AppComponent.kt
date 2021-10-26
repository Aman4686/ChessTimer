package com.example.chesstimer

import com.example.chesstimer.features.creator.CreatorViewModel
import com.example.chesstimer.daggerModule.NavModule
import com.example.chesstimer.features.settings.ListViewModel
import com.example.chesstimer.features.timer.TimerViewModel
import com.example.chesstimer.daggerModule.DataBaseModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NavModule::class , DataBaseModule::class])
@Singleton
interface AppComponent {
    fun inject(timerViewModel: TimerViewModel)
    fun inject(settingViewModel: ListViewModel)
    fun inject(creatorViewModel: CreatorViewModel)
}