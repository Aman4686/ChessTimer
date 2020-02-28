package com.example.chesstimer.features.settings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import com.example.chesstimer.R
import com.example.chesstimer.basic.BaseView
import com.example.chesstimer.databinding.SettingLayoutBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SettingView(@NonNull inflater: LayoutInflater, @NonNull lifecycleOwner: LifecycleOwner,
                  @Nullable container: ViewGroup, @NonNull val model: SettingViewModel) : BaseView() {


    lateinit var createNewSettings : FloatingActionButton

    init {
        val mDataBinding : SettingLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.setting_layout, container, false)
        mDataBinding.lifecycleOwner = lifecycleOwner
        mDataBinding.executePendingBindings()
        mDataBinding.viewModel = model
        viewLayout = mDataBinding.root
        initIds()
    }
    private fun initIds() {
        createNewSettings = viewLayout.findViewById(R.id.setting_create)
    }

}