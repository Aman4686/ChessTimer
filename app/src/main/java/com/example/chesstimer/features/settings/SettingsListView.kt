package com.example.chesstimer.features.settings

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chesstimer.R
import com.example.chesstimer.basic.BaseView
import com.example.chesstimer.databinding.SettingLayoutBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SettingsListView(@NonNull inflater: LayoutInflater, @NonNull lifecycleOwner: LifecycleOwner,
                       @Nullable container: ViewGroup, @NonNull val model: SettingsListViewModel) : BaseView() {


    lateinit var createNewSettings : FloatingActionButton
    lateinit var settingRecycler : RecyclerView

    init {
        val mDataBinding : SettingLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.setting_layout, container, false)
        mDataBinding.lifecycleOwner = lifecycleOwner
        mDataBinding.executePendingBindings()
        mDataBinding.viewModel = model
        viewLayout = mDataBinding.root
        val context : Context = viewLayout.context
        initIds()
        val adapter = model.adapter

        settingRecycler.layoutManager = LinearLayoutManager(context)
        settingRecycler.adapter = adapter

    }
    private fun initIds() {
        createNewSettings = viewLayout.findViewById(R.id.setting_create)
        settingRecycler = viewLayout.findViewById(R.id.setting_recycler)
    }

}