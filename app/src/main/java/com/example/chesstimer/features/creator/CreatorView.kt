package com.example.chesstimer.features.creator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import com.example.chesstimer.R
import com.example.chesstimer.basic.BaseView
import com.example.chesstimer.databinding.CreatorLayoutBinding

class CreatorView(@NonNull inflater: LayoutInflater, @NonNull lifecycleOwner: LifecycleOwner,
                  @Nullable container: ViewGroup, @NonNull val model: CreatorViewModel) : BaseView() {


    init {
        val mDataBinding : CreatorLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.creator_layout, container, false)
        mDataBinding.lifecycleOwner = lifecycleOwner
        mDataBinding.executePendingBindings()
        mDataBinding.viewModel = model
        viewLayout = mDataBinding.root
    }




}