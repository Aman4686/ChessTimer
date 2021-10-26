package com.example.chesstimer.features.timer


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import butterknife.BindView
import butterknife.ButterKnife


import com.example.chesstimer.R
import com.example.chesstimer.basic.BaseView
import com.example.chesstimer.basic.BaseViewModel
import com.example.chesstimer.common.states.TimerState
import com.example.chesstimer.databinding.TimerLayoutBinding


class TimerView(inflater: LayoutInflater, lifecycleOwner: LifecycleOwner,
                 container: ViewGroup , model: TimerViewModel) : BaseView() {

    @BindView(R.id.bottom_primary_timer)
    lateinit var bottomPrimaryTimer : TextView

    @BindView(R.id.top_primary_timer)
    lateinit var topPrimaryTimer : TextView

    @BindView(R.id.bottom_secondary_timer)
    lateinit var bottomSecondaryTimer : TextView

    @BindView(R.id.top_secondary_timer)
    lateinit var topSecondaryTimer : TextView


    override fun initViewBinding(inflater: LayoutInflater, lifecycleOwner: LifecycleOwner,
                                 container: ViewGroup, model: BaseViewModel){
        val mDataBinding : TimerLayoutBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        mDataBinding.lifecycleOwner = lifecycleOwner
        mDataBinding.executePendingBindings()
        mDataBinding.viewModel = model as TimerViewModel
        viewLayout = mDataBinding.root
        unbinder = ButterKnife.bind(this , viewLayout)
        context = mDataBinding.root.context
    }

    init {
        initViewBinding(inflater , lifecycleOwner , container , model)

        val timer = model.timers
        timer.bind(bottomPrimaryTimer , topPrimaryTimer,
            bottomSecondaryTimer , topSecondaryTimer)
        model.initTime(timer)

        model.timerStateObserver.observe(lifecycleOwner, Observer {

            if(it.timerState == TimerState.PAUSED)
                model.timers.pausedTimers()

            if(it.timerState == TimerState.RUNNING)
                model.timers.startTimer(it.gameTurnState)

            if(it.timerState == TimerState.RESETED)
                model.timers.refreshTimers()

        })
    }

    override fun getLayoutId(): Int {
        return R.layout.timer_layout
    }

}





