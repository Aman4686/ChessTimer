package com.example.chesstimer.timer

import android.os.Bundle
import android.text.format.Time
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chesstimer.MainActivity
import com.example.chesstimer.basic.BaseFragment
import javax.inject.Inject

class TimerFragment : BaseFragment() {

    @Inject
    lateinit var model : TimerViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        MainActivity.appComponent.injectTimerFragment(this)

        val timerView = TimerView(inflater ,this , container!! , model)

        return timerView.viewLayout
    }
}