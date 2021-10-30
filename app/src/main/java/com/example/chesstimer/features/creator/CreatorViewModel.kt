package com.example.chesstimer.features.creator

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chesstimer.basic.BaseViewModel
import com.example.chesstimer.common.PrefUtils
import com.example.chesstimer.common.navigation.TimerNavigator
import com.example.chesstimer.dataBase.DataBaseRepo
import com.example.chesstimer.dataBase.dao.SettingEntity
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class CreatorViewModel constructor(val navigator : TimerNavigator,
                                   val data : DataBaseRepo): BaseViewModel() {

    val settingLiveData = MutableLiveData<SettingEntity>()


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
        data.insertSetting(
            SettingEntity(
                title,
                time
            )
        )
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
    class Factory @Inject constructor(
        private val navigator: TimerNavigator,
        private val dataBaseRepo: DataBaseRepo
    ): ViewModelProvider.Factory{
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            require(modelClass == CreatorViewModel::class.java)
            return CreatorViewModel(navigator, dataBaseRepo) as T
        }
    }


}