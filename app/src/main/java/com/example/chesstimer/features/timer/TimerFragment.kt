package com.example.chesstimer.features.timer

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.Lazy
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import butterknife.Unbinder
import com.example.chesstimer.MainActivity.Companion.appComponent
import com.example.chesstimer.base.BaseFragment
import com.example.chesstimer.common.LogUtils
import com.example.chesstimer.common.states.TimerState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

import javax.inject.Inject


class TimerFragment : BaseFragment() {

    lateinit var model : TimerViewModel
    lateinit var unbinder: Unbinder

    @Inject
    lateinit var factory: Lazy<TimerViewModel.Factory>

    override fun onAttach(context: Context) {
        Log.d("FragmentLifecycle", "onAttach: ")
        appComponent.inject(this)
        super.onAttach(context)
    }

    lateinit var timer : CountDownTimers

    private fun initCountDownTimer(model: TimerViewModel){
        timer = CountDownTimers(model)
    }

    private fun initCollectors(){
        lifecycleScope.launch {
            model.timerStateObserver.collect {
                Log.d("FragmentLifecycle", "update State ")
                when (it.timerState) {
                    TimerState.PAUSED -> timer.pausedTimers()
                    TimerState.RUNNING -> timer.startTimer(it.gameTurnState)
                    TimerState.RESETED -> timer.refreshState()
                    TimerState.FINISHED -> timer.pausedTimers()
                }
            }
        }

        lifecycleScope.launch {
            model.settingStateFlow
                .collect {
                    Log.d("FragmentLifecycle", "update time ")
                    model.initPlayersTime(it.timeDuration)
                    timer.refreshTimers(it.timeDuration)
                }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("FragmentLifecycle", "onCreate: ")
        super.onCreate(savedInstanceState)
        model = ViewModelProvider(this, factory.get())[TimerViewModel::class.java]
        initCountDownTimer(model)
        initCollectors()
    }

    override fun onStart() {
        Log.d("FragmentLifecycle", "onStart: ")
        super.onStart()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d("FragmentLifecycle", "onCreateView: ")
        val timerView = TimerView(inflater ,this , container!! , model)
        this.unbinder = timerView.unbinder
        return timerView.viewLayout
    }

    override fun onDestroyView() {
        Log.d("FragmentLifecycle", "onDestroyView: ")
        super.onDestroyView()
        unbinder.unbind()
    }

    override fun onStop() {
        Log.d("FragmentLifecycle", "onStop: ")
        super.onStop()
        model.pausedTimer()
    }

    override fun onPause() {
        Log.d("FragmentLifecycle", "onPause: ")
        super.onPause()
    }

    override fun onResume() {
        Log.d("FragmentLifecycle", "onResume: ")
        super.onResume()
    }
}


