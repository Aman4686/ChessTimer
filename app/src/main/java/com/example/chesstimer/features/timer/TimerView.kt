package com.example.chesstimer.features.timer

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

import com.example.chesstimer.R
import com.example.chesstimer.basic.BaseView
import com.example.chesstimer.common.states.TimerState
import com.example.chesstimer.databinding.TimerLayoutBinding


class TimerView(@NonNull inflater: LayoutInflater, @NonNull lifecycleOwner: LifecycleOwner,
                 @Nullable container: ViewGroup , @NonNull val model: TimerViewModel) : BaseView() {

    private lateinit var bottomPrimaryTimer : TextView
    private lateinit var topPrimaryTimer : TextView
    private lateinit var bottomSecondaryTimer : TextView
    private lateinit var topSecondaryTimer : TextView

    private val context : Context

    init {
        val mDataBinding : TimerLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.timer_layout, container, false)
        mDataBinding.lifecycleOwner = lifecycleOwner
        mDataBinding.executePendingBindings()
        mDataBinding.viewModel = model
        viewLayout = mDataBinding.root

        context = mDataBinding.root.context
        initIds()

        val timer = CountDownTimers(
            bottomPrimaryTimer, topPrimaryTimer,
            bottomSecondaryTimer, topSecondaryTimer,
            model)

        model.initTime(timer)
        model.timerStateObserver.observe(lifecycleOwner, Observer {

            if(it.timerState == TimerState.PAUSED)
                timer.pausedTimers()

            if(it.timerState == TimerState.RUNNING)
                timer.startTimer(it.gameTurnState)

            if(it.timerState == TimerState.RESETED)
                timer.refreshTimers()

        })
    }

    private fun initIds() {
        bottomPrimaryTimer = viewLayout.findViewById(R.id.bottom_primary_timer)
        topPrimaryTimer = viewLayout.findViewById(R.id.top_primary_timer)
        bottomSecondaryTimer = viewLayout.findViewById(R.id.bottom_secondary_timer)
        topSecondaryTimer = viewLayout.findViewById(R.id.top_secondary_timer)
    }
}





