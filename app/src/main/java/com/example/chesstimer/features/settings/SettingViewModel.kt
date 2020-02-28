package com.example.chesstimer.features.settings

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.chesstimer.MainActivity
import com.example.chesstimer.common.navigation.TimerNavigator

import javax.inject.Inject

class SettingViewModel : ViewModel(){
    @Inject
    lateinit var navigator : TimerNavigator

    init {
        MainActivity.appComponent.inject(this)
    }

    fun onBackClicked(v : View){
        navigator.navigateBack()
    }


    fun onCreatorClicked(v : View){
        navigator.navigateToCreator()
    }



  //  val timerDataModel = MutableLiveData(TimerData())
}