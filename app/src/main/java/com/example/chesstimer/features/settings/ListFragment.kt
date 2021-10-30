package com.example.chesstimer.features.settings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.chesstimer.MainActivity
import com.example.chesstimer.MainActivity.Companion.appComponent
import com.example.chesstimer.basic.BaseFragment
import com.example.chesstimer.features.timer.TimerViewModel
import dagger.Lazy
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class ListFragment : BaseFragment(){

    lateinit var model : ListViewModel

    @Inject
    lateinit var factory: Lazy<TimerViewModel.Factory>

    override fun onAttach(context: Context) {
        appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProvider(this, factory.get())[ListViewModel::class.java]

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val settingView = ListView(inflater ,this , container!! , model)
        Completable.complete()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                model.settingListModel.observe(viewLifecycleOwner, Observer {
                model.adapter.initSettingList(it)
            }) }
        return settingView.viewLayout
    }
}