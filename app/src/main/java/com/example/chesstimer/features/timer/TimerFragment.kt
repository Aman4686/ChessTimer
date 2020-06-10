package com.example.chesstimer.features.timer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.chesstimer.basic.BaseFragment
import com.example.chesstimer.common.states.TimerState


class TimerFragment : BaseFragment() {

    lateinit var model : TimerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProvider(this)[TimerViewModel::class.java]
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val timerView = TimerView(inflater ,this , container!! , model)
        return timerView.viewLayout
    }

    override fun onStop() {
        super.onStop()
        model.pausedTimer()
    }
}