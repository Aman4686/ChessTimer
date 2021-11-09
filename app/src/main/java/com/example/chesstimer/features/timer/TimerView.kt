package com.example.chesstimer.features.timer


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import butterknife.BindView
import butterknife.ButterKnife


import com.example.chesstimer.R
import com.example.chesstimer.base.BaseView
import com.example.chesstimer.base.BaseViewModel
import com.example.chesstimer.common.states.TimerState
import com.example.chesstimer.databinding.TimerLayoutBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


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

    lateinit var timer : CountDownTimers

    fun initViewBinding(inflater: LayoutInflater, lifecycleOwner: LifecycleOwner,
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
        initCountDownTimer(model)

    }

    fun initCountDownTimer(model: TimerViewModel){
        timer = CountDownTimers(model)
    }

    override fun getLayoutId(): Int {
        return R.layout.timer_layout
    }

}





