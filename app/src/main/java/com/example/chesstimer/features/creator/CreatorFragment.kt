package com.example.chesstimer.features.creator

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.chesstimer.MainActivity.Companion.appComponent
import com.example.chesstimer.base.BaseFragment
import com.example.chesstimer.features.creator.CreatorView.Companion.trimThis
import dagger.Lazy
import javax.inject.Inject

class CreatorFragment : BaseFragment() {
    lateinit var model : CreatorViewModel

    @Inject
    lateinit var factory: Lazy<CreatorViewModel.Factory>

    override fun onAttach(context: Context) {
        appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProvider(this, factory.get())[CreatorViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val creatorView = CreatorView(inflater ,this , container!! , model , fragmentManager)

        return creatorView.viewLayout
    }
}