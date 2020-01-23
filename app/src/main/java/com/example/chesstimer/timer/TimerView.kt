package com.example.chesstimer.timer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import com.example.chesstimer.Base.BaseView
import com.example.chesstimer.R
import com.example.chesstimer.databinding.TimerMainBinding

class TimerView(@NonNull inflater: LayoutInflater, @NonNull lifecycleOwner: LifecycleOwner,
                 @Nullable container: ViewGroup , @NonNull val model: TimerViewModel) : BaseView() {
    init {
        val mDataBinding : TimerMainBinding = DataBindingUtil.inflate(inflater, R.layout.timer_main, container, false)
        mDataBinding.lifecycleOwner = lifecycleOwner
        mDataBinding.executePendingBindings()
        mDataBinding.viewModel = model
        viewLayout = mDataBinding.root
    }
}




