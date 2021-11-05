package com.example.chesstimer.features.creator

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chesstimer.base.BaseViewModel
import com.example.chesstimer.common.PrefUtils
import com.example.chesstimer.common.navigation.TimerNavigator
import com.example.chesstimer.dataBase.SettingRepo
import com.example.chesstimer.dataBase.dao.SettingEntity
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class CreatorViewModel (
    val navigator : TimerNavigator,
    val data : SettingRepo
): BaseViewModel() {

    val settingLiveData = MutableLiveData<SettingEntity>()


    fun initCreatorEntity(){
        val gameId = PrefUtils.getGameConfig()
        data.getSettingById(gameId).subscribeBy{
            settingLiveData.value = it
        }
    }

    fun onSaveClicked(v : View){
        val settingItem = settingLiveData.value
        if(settingItem != null){
            data.updateSetting(settingItem)
        }
    }

    fun updateSetting(settingItem : SettingEntity){
        data.updateSetting(settingItem)
    }

//    fun onAddToListClicked(title : String , time : Long){
//        data.insertSetting(
//            SettingEntity(
//                 time
//            )
//        )
//    }

    fun onBackClicked(v : View){
        navigator.navigateBack()
    }

    fun onListClicked(v : View){
     navigator.navigateToSettingsList()
    }

    class Factory @Inject constructor(
        private val navigator: TimerNavigator,
        private val settingRepo: SettingRepo
    ): ViewModelProvider.Factory{
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            require(modelClass == CreatorViewModel::class.java)
            return CreatorViewModel(navigator, settingRepo) as T
        }
    }


}