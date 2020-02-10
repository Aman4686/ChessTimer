package com.example.chesstimer.timer

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
import com.example.chesstimer.common.timer.TimerState
import com.example.chesstimer.databinding.TimerMainBinding


class TimerView(@NonNull inflater: LayoutInflater, @NonNull lifecycleOwner: LifecycleOwner,
                 @Nullable container: ViewGroup , @NonNull val model: TimerViewModel) : BaseView() {

    private var gameTime = model.timerLiveDataModel.value?.maxTimeCountMillis?:10000L

    private lateinit var bottomPrimaryTimer : TextView
    private lateinit var topPrimaryTimer : TextView
    private lateinit var bottomSecondaryTimer : TextView
    private lateinit var topSecondaryTimer : TextView

    private val context : Context

    init {
        val mDataBinding : TimerMainBinding = DataBindingUtil.inflate(inflater, R.layout.timer_main, container, false)
        mDataBinding.lifecycleOwner = lifecycleOwner
        mDataBinding.executePendingBindings()
        mDataBinding.viewModel = model

        viewLayout = mDataBinding.root
        context = mDataBinding.root.context
        initIds()
        val timer = Timers(
            gameTime, bottomPrimaryTimer, topPrimaryTimer,
            bottomSecondaryTimer, topSecondaryTimer
        )

        model.timerLiveDataModel.observe(lifecycleOwner, Observer {

            if(it.timerState == TimerState.PAUSED) {
                timer.pausedTimers()
            }

            if(it.timerState == TimerState.RUNNING)
                timer.startTimer(it.gameState)

            if(it.timerState == TimerState.RESETED) {
                timer.resetTimers()
            }
        })
    }

    private fun initIds() {
        bottomPrimaryTimer = viewLayout.findViewById(R.id.bottom_primary_timer)
        topPrimaryTimer = viewLayout.findViewById(R.id.top_primary_timer)
        bottomSecondaryTimer = viewLayout.findViewById(R.id.bottom_secondary_timer)
        topSecondaryTimer = viewLayout.findViewById(R.id.top_secondary_timer)
    }
}





