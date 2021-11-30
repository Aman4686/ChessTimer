package com.example.chesstimer.features.timer

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import dagger.Lazy
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.chesstimer.MainActivity.Companion.appComponent
import com.example.chesstimer.R
import com.example.chesstimer.common.LogUtils
import com.example.chesstimer.common.states.TimerState
import com.example.chesstimer.databinding.ListLayoutBinding
import com.example.chesstimer.databinding.TimerLayoutBinding
import com.example.chesstimer.features.settings.ListViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

import javax.inject.Inject


class TimerFragment : Fragment(R.layout.timer_layout) {

    private lateinit var binding: TimerLayoutBinding
    private lateinit var model : TimerViewModel
    private lateinit var timer : CountDownTimers

    @Inject
    lateinit var factory: Lazy<TimerViewModel.Factory>

    override fun onAttach(context: Context) {
        appComponent.inject(this)
        super.onAttach(context)
    }

    private fun initCountDownTimer(model: TimerViewModel){
        timer = CountDownTimers(model)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        model = ViewModelProvider(this, factory.get())[TimerViewModel::class.java]
        initCountDownTimer(model)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = TimerLayoutBinding.bind(view)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.executePendingBindings()
        binding.viewModel = model

        initCollectors()

    }


    private fun initCollectors(){
        viewLifecycleOwner.lifecycleScope.launch {
            model.timerStateObserver.collect {
                when (it.timerState) {
                    TimerState.PAUSED -> timer.pausedTimers()
                    TimerState.RUNNING -> timer.startTimer(it.gameTurnState)
                    TimerState.RESETED -> timer.refreshState()
                    TimerState.FINISHED -> timer.pausedTimers()
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            model.settingStateFlow
                .collect {
                    model.initPlayersTime(it.timeDuration)
                    timer.refreshTimers(it.timeDuration)
                }
        }
    }


    override fun onStop() {
        super.onStop()
        model.pausedTimer()
    }

}


