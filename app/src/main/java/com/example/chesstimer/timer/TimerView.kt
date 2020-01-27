package com.example.chesstimer.timer

import android.content.Context
import android.os.CountDownTimer
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
import com.example.chesstimer.common.timer.GameState
import com.example.chesstimer.common.timer.TimerState
import com.example.chesstimer.databinding.TimerMainBinding
import java.util.*

class TimerView(@NonNull inflater: LayoutInflater, @NonNull lifecycleOwner: LifecycleOwner,
                 @Nullable container: ViewGroup , @NonNull val model: TimerViewModel) : BaseView() {

    private var gameTime = model.timerModel.value?.maxTimeCountMillis?:10000L

    private var bottomPlayerTimeLeft = gameTime
    private var topPlayerTimeLeft = gameTime

    private lateinit var bottomPrimaryTimer : TextView
    private lateinit var topPrimaryTimer : TextView
    private lateinit var bottomSecondaryTimer : TextView
    private lateinit var topSecondaryTimer : TextView

    private var timerTop : CountDownTimer? = null
    private var timerBottom : CountDownTimer? = null

    private val context : Context

    init {
        val mDataBinding : TimerMainBinding = DataBindingUtil.inflate(inflater, R.layout.timer_main, container, false)
        mDataBinding.lifecycleOwner = lifecycleOwner
        mDataBinding.executePendingBindings()
        mDataBinding.viewModel = model

        viewLayout = mDataBinding.root
        context = mDataBinding.root.context
        initIds()
        prepareTimerTopTimer()
        prepareTimerBottomTimer()
        updateBottomTimer()
        updateTopTimer()

        model.timerModel.observe(lifecycleOwner, Observer {
            if(it.timerState == TimerState.PAUSED) {
                timerTop?.cancel()
                timerBottom?.cancel()
            }

            if(it.timerState == TimerState.RUNNING)
                startTimer(it.gameState)

            if(it.timerState == TimerState.RESETED) {
                gameTime = it.maxTimeCountMillis
                timerBottom?.onFinish()
                timerTop?.onFinish()
                updateBottomTimer()
                updateTopTimer()
            }
        })
    }

    private fun initIds() {
        bottomPrimaryTimer = viewLayout!!.findViewById(R.id.bottom_primary_timer)
        topPrimaryTimer = viewLayout!!.findViewById(R.id.top_primary_timer)
        bottomSecondaryTimer = viewLayout!!.findViewById(R.id.bottom_secondary_timer)
        topSecondaryTimer = viewLayout!!.findViewById(R.id.top_secondary_timer)
    }

    private fun updateTopTimer() {
        val minutes : Int = (topPlayerTimeLeft / 1000 / 60).toInt()
        val seconds : Int = (topPlayerTimeLeft / 1000 % 60).toInt()

        val timeLeftFormatted = String.format(Locale.getDefault() ,"%01d:%02d" , minutes , seconds)
        topPrimaryTimer.text = timeLeftFormatted
        bottomSecondaryTimer.text = timeLeftFormatted
    }

    private fun updateBottomTimer() {
        val minutes : Int = (bottomPlayerTimeLeft / 1000 / 60).toInt()
        val seconds : Int = (bottomPlayerTimeLeft / 1000 % 60).toInt()

        val timeLeftFormatted = String.format(Locale.getDefault() ,"%01d:%02d" , minutes , seconds)
        bottomPrimaryTimer.text = timeLeftFormatted
        topSecondaryTimer.text = timeLeftFormatted
    }

    private fun prepareTimerTopTimer(){
        timerTop = object : CountDownTimer(topPlayerTimeLeft , 1000){
            override fun onFinish() {
                timerTop?.cancel()
                topPlayerTimeLeft = gameTime
            }

            override fun onTick(millisUntilFinished: Long) {
                topPlayerTimeLeft = millisUntilFinished
                updateTopTimer()
            }
        }
    }

    private fun prepareTimerBottomTimer(){
        timerBottom = object : CountDownTimer(bottomPlayerTimeLeft , 1000){
            override fun onFinish() {
                timerTop?.cancel()
                bottomPlayerTimeLeft = gameTime
            }

            override fun onTick(millisUntilFinished: Long) {
                bottomPlayerTimeLeft = millisUntilFinished
                updateBottomTimer()
            }
        }
    }

    private fun startTimer(gameState: GameState) {
        when(gameState){
            GameState.PLAYER_TOP -> {
                prepareTimerTopTimer()
                timerTop?.start()
                timerBottom?.cancel()
            } GameState.PLAYER_BOTTOM -> {
                prepareTimerBottomTimer()
                timerBottom?.start()
                timerTop?.cancel()
            }
        }
    }
}





