package com.example.chesstimer

import android.content.Context
import androidx.navigation.NavController
import com.example.chesstimer.di.AppModule
import com.example.chesstimer.features.creator.CreatorFragment
import com.example.chesstimer.features.settings.ListFragment
import com.example.chesstimer.features.timer.TimerFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    fun inject(timerFragment: TimerFragment)
    fun inject(listFragment: ListFragment)
    fun inject(creatorFragment: CreatorFragment)


    @Component.Builder
    interface Builder{

        @BindsInstance
        fun activity(activity: MainActivity): Builder

        @BindsInstance
        fun appContext(appContext: Context): Builder

        fun build(): AppComponent
    }

}