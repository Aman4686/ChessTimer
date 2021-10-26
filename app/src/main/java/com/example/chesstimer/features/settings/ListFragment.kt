package com.example.chesstimer.features.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.chesstimer.basic.BaseFragment
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers

class ListFragment : BaseFragment(){

    lateinit var model : ListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProvider(this)[ListViewModel::class.java]

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val settingView = ListView(inflater ,this , container!! , model)
        Completable.complete()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                model.settingListModel.observe(this, Observer {
                model.adapter.initSettingList(it)
            }) }
        return settingView.viewLayout
    }
}