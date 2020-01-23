package com.example.chesstimer.timer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chesstimer.Base.BaseFragment

class TimerFragment(private val navigation: Lazy<TimerViewModel>) : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val model = navigation.value

        val timerView = TimerView(inflater ,this , container!! , model)

        return timerView.viewLayout
    }
}