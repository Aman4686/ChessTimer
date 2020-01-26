package com.example.chesstimer

import android.os.Bundle
import com.example.chesstimer.basic.BaseActivity
import com.example.chesstimer.common.KoinFragmentFactory
import com.example.chesstimer.common.navigation.TimerNavigation
import com.example.chesstimer.common.navigation.TimerNavigator

class MainActivity : BaseActivity() {
    private lateinit var timerNavigator : TimerNavigator
    override fun onCreate(savedInstanceState: Bundle?) {
        createScope()
        supportFragmentManager.fragmentFactory = KoinFragmentFactory(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_container_fragment)
        timerNavigator = instanceOf(TimerNavigation::class.java)
    }
}
