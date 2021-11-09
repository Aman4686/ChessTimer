package com.example.chesstimer.features.creator

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.chesstimer.base.BaseViewModel
import com.example.chesstimer.common.PrefUtils
import com.example.chesstimer.common.navigation.TimerNavigator
import com.example.chesstimer.dataBase.SettingRepo
import com.example.chesstimer.dataBase.dao.SettingEntity
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreatorViewModel (
    val navigator : TimerNavigator,
    val data : SettingRepo
): BaseViewModel() {


    val settingLiveData = MutableStateFlow(SettingEntity(120000))

    init {
        initCreatorEntity()
    }

    fun initCreatorEntity(){
        val gameId = PrefUtils.getGameConfig()
        viewModelScope.launch(Dispatchers.IO) {
            data.getSettingById(gameId)
                .collect{  settingLiveData.value = it  }
        }
     }

    fun onSaveClicked(v : View){
        val settingItem = settingLiveData.value
        viewModelScope.launch {
            data.updateSetting(settingItem)
        }

    }

    fun updateSetting(settingItem : SettingEntity){
        viewModelScope.launch {
            data.updateSetting(settingItem)
        }
    }


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