package com.example.chesstimer.basic

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import butterknife.ButterKnife
import butterknife.Unbinder
import com.example.chesstimer.databinding.TimerLayoutBinding
import com.example.chesstimer.features.timer.TimerViewModel

abstract class BaseView {
    lateinit var viewLayout: View

    lateinit var context : Context
    lateinit var unbinder: Unbinder

    abstract fun getLayoutId(): Int

    abstract fun initViewBinding(inflater: LayoutInflater, lifecycleOwner: LifecycleOwner,
                                container: ViewGroup, model: BaseViewModel)





}