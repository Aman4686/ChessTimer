package com.example.chesstimer.features.creator

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chesstimer.MainActivity
import com.example.chesstimer.basic.BaseViewModel
import com.example.chesstimer.common.PrefUtils
import com.example.chesstimer.common.navigation.TimerNavigator
import com.example.chesstimer.dataBase.DataBaseRepo
import com.example.chesstimer.dataBase.SettingEntity
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class CreatorViewModel : BaseViewModel() {

    @Inject
    lateinit var navigator : TimerNavigator

    @Inject
    lateinit var data : DataBaseRepo

    val settingLiveData = MutableLiveData<SettingEntity>()

    init {
        MainActivity.appComponent.inject(this)
            //  initCreator()
    }

    fun onSaveClicked(title : String, time : Long){
        val settingItem = settingLiveData.value
        if(settingItem != null){
            settingItem.title = title
            settingItem.timeDuration = time
            data.updateSetting(settingItem)
            navigator.navigateBack()
        }
    }

    fun onAddToListClicked(title : String , time : Long){
        data.insertSetting(SettingEntity(title, time))
    }

    fun onBackClicked(v : View){
        navigator.navigateBack()
    }

    fun onListClicked(v : View){
     navigator.navigateToSettingsList()
    }

    fun initCreator(){
        val gameId = PrefUtils.getGameConfig()
        data.getSettingById(gameId).subscribeBy{
            settingLiveData.value = it
        }
    }



}