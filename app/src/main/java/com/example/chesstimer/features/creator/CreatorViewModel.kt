package com.example.chesstimer.features.creator

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.chesstimer.MainActivity
import com.example.chesstimer.common.navigation.TimerNavigator
import com.example.chesstimer.dataBase.DataBaseRepo
import com.example.chesstimer.dataBase.SettingEntity
import javax.inject.Inject

class CreatorViewModel : ViewModel() {

    @Inject
    lateinit var navigator : TimerNavigator
    @Inject

    lateinit var data : DataBaseRepo

    init {
        MainActivity.appComponent.inject(this)
    }

    fun onSaveCliked(title : String , time : Long){
        data.insert(SettingEntity(title , time))
        navigator.navigateBack()
    }



}