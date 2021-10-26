package com.example.chesstimer.features.timer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import butterknife.ButterKnife
import butterknife.Unbinder
import com.example.chesstimer.basic.BaseFragment
import com.example.chesstimer.common.states.TimerState


class TimerFragment : BaseFragment() {

    lateinit var model : TimerViewModel
    lateinit var unbinder: Unbinder


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProvider(this)[TimerViewModel::class.java]
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val timerView = TimerView(inflater ,this , container!! , model)
        this.unbinder = timerView.unbinder
        return timerView.viewLayout
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unbinder.unbind()
    }

    override fun onStop() {
        super.onStop()
        model.pausedTimer()
    }
}