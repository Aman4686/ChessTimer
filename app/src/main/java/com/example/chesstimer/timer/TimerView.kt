package com.example.chesstimer.timer

import android.content.Context
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.observe

import com.example.chesstimer.R
import com.example.chesstimer.basic.BaseView
import com.example.chesstimer.databinding.TimerMainBinding
import java.util.*
import kotlinx.android.synthetic.*

class TimerView(@NonNull inflater: LayoutInflater, @NonNull lifecycleOwner: LifecycleOwner,
                 @Nullable container: ViewGroup , @NonNull val model: TimerViewModel) : BaseView() {

    private var timerLengthSeconds = 300000L
    private var secondsLeft = 300000L

    private lateinit var bottomTimer : TextView
    private lateinit var btnPauseTimer : TextView
    private lateinit var btnStartTimer : TextView
    private lateinit var btnResetTimer : TextView

    private lateinit var timer : CountDownTimer
   // private var timerState = TimerState.STOPPED

    private val context : Context

    init {
        val mDataBinding : TimerMainBinding = DataBindingUtil.inflate(inflater, R.layout.timer_main, container, false)
        mDataBinding.lifecycleOwner = lifecycleOwner
        mDataBinding.executePendingBindings()
        mDataBinding.viewModel = model

        viewLayout = mDataBinding.root
        context = mDataBinding.root.context

        initIds()
        initClickListeners()

        model.timerModel.observe(lifecycleOwner, Observer {

        })



    }

    private fun initIds() {
        bottomTimer = viewLayout!!.findViewById(R.id.bottom_timer)
        btnPauseTimer = viewLayout!!.findViewById(R.id.pause_timer)
        btnStartTimer = viewLayout!!.findViewById(R.id.start_timer)
        btnResetTimer = viewLayout!!.findViewById(R.id.reset_timer)
    }

    private fun initClickListeners() {
        btnStartTimer.setOnClickListener {
            startTimer()
             //  timerState = TimerState.RUNNING
        }
        btnPauseTimer.setOnClickListener {
            timer.cancel()
            //   timerState = TimerState.PAUSED
        }
        btnResetTimer.setOnClickListener {
            timer.onFinish()
            updateCountDownText()
        }
    }

    private fun updateCountDownText() {
        val minutes : Int = (secondsLeft / 1000 / 60).toInt()
        val seconds : Int = (secondsLeft / 1000 % 60).toInt()

        val timeLeftFormatted = String.format(Locale.getDefault() ,"%01d:%02d" , minutes , seconds)
        bottomTimer.text = timeLeftFormatted
    }

    private fun startTimer() {
        timer = object : CountDownTimer(secondsLeft , 1000){
            override fun onFinish() {
                timer.cancel()
                secondsLeft = timerLengthSeconds
            }

            override fun onTick(millisUntilFinished: Long) {
                secondsLeft = millisUntilFinished
                updateCountDownText()
            }
        }.start()

    }
}





