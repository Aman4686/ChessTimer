package com.example.chesstimer.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import butterknife.Unbinder

abstract class BaseView {
    lateinit var viewLayout: View

    lateinit var context : Context
    lateinit var unbinder: Unbinder

    abstract fun getLayoutId(): Int

    abstract fun initViewBinding(inflater: LayoutInflater, lifecycleOwner: LifecycleOwner,
                                container: ViewGroup, model: BaseViewModel)





}