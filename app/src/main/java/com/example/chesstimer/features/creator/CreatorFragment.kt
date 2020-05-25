package com.example.chesstimer.features.creator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.chesstimer.basic.BaseFragment

class CreatorFragment : BaseFragment() {
    lateinit var model : CreatorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProvider(this)[CreatorViewModel::class.java]

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val creatorView = CreatorView(inflater ,this , container!! , model , fragmentManager)

        return creatorView.viewLayout
    }
}