package com.example.chesstimer.features.settings

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chesstimer.MainActivity
import com.example.chesstimer.common.PrefUtils
import com.example.chesstimer.common.navigation.TimerNavigator
import com.example.chesstimer.dataBase.DataBaseRepo
import com.example.chesstimer.dataBase.SettingEntity
import io.reactivex.rxkotlin.subscribeBy

import javax.inject.Inject

class SettingsListViewModel : ViewModel(){
    @Inject
    lateinit var navigator : TimerNavigator

    @Inject
    lateinit var data : DataBaseRepo

    val settingListModel = MutableLiveData<List<SettingEntity>>()
    val adapter = SettingsListRecyclerAdapter()

    init {
        MainActivity.appComponent.inject(this)
        adapter.checkedPosition = PrefUtils.getGameConfig()
        initList()
    }

    fun initList() {
       data.getAllSetings().subscribeBy {
            settingListModel.value = it
        }
    }

    fun onStartClicked(v : View){
        val id = adapter.getSelectedItemId()
        PrefUtils.addGameConfig(id)
        navigator.navigateBack()
    }


    fun onCreatorClicked(v : View){
        navigator.navigateToCreator()
    }




}