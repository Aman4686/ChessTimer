package com.example.chesstimer.features.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.chesstimer.basic.BaseFragment

class SettingFragment : BaseFragment(){

    lateinit var model : SettingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProvider(this)[SettingViewModel::class.java]
        model.settingListModel.observe(this, Observer {
            model.adapter.initSettingList(it)
        })
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val settingView = SettingView(inflater ,this , container!! , model)

        return settingView.viewLayout
    }
}