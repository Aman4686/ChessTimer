package com.example.chesstimer.features.creator

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.chesstimer.MainActivity
import com.example.chesstimer.common.navigation.TimerNavigator
import javax.inject.Inject

class CreatorViewModel : ViewModel() {

    @Inject
    lateinit var navigator : TimerNavigator

    init {
        MainActivity.appComponent.inject(this)
    }

    fun onSaveCliked(title : String , time : Int){



        navigator.navigateBack()
    }



}