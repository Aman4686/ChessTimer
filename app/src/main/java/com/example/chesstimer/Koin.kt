package com.example.chesstimer

import com.example.chesstimer.common.navigation.TimerNavigation
import com.example.chesstimer.common.navigation.TimerNavigator
import com.example.chesstimer.timer.TimerFragment
import com.example.chesstimer.timer.TimerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


@JvmField
val koinModule = module {

    single { TimerNavigation(get()) }

   viewModel{ TimerViewModel(get()) }

    scope(named<MainActivity>()){
        factory { TimerFragment(inject()) }
        scoped<TimerNavigator> {TimerNavigation(get()) }
    }


}