package com.example.chesstimer.timer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.chesstimer.basic.BaseFragment


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
}