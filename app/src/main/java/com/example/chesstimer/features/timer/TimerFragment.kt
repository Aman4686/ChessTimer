package com.example.chesstimer.features.timer

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.Lazy
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import butterknife.ButterKnife
import butterknife.Unbinder
import com.example.chesstimer.MainActivity.Companion.appComponent
import com.example.chesstimer.basic.BaseFragment

import com.example.chesstimer.common.states.TimerState
import com.example.chesstimer.features.settings.ListViewModel
import javax.inject.Inject


class TimerFragment : BaseFragment() {

    lateinit var model : TimerViewModel
    lateinit var unbinder: Unbinder

    @Inject
    lateinit var factory: Lazy<TimerViewModel.Factory>

    override fun onAttach(context: Context) {
        appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProvider(this, factory.get())[TimerViewModel::class.java]

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val timerView = TimerView(inflater ,this , container!! , model)
        this.unbinder = timerView.unbinder
        return timerView.viewLayout
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unbinder.unbind()
    }

    override fun onStop() {
        super.onStop()
        model.pausedTimer()
    }
}


