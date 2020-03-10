package com.example.chesstimer.features.settings

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chesstimer.MainActivity
import com.example.chesstimer.common.PrefUtils
import com.example.chesstimer.common.navigation.TimerNavigator
import com.example.chesstimer.data.SettingData
import com.example.chesstimer.dataBase.DataBaseRepo
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy

import javax.inject.Inject

class SettingViewModel : ViewModel(){
    @Inject
    lateinit var navigator : TimerNavigator

    @Inject
    lateinit var data : DataBaseRepo

    val settingListModel = MutableLiveData<List<SettingData>>()
    val adapter = SettingRecyclerAdapter()

    init {
        MainActivity.appComponent.inject(this)
        initList()
    }

    fun initList() {
       data.getAll().subscribeBy {
            settingListModel.value = it
        }
    }

    fun onStartClicked(v : View){
        PrefUtils.addGameConfig(adapter.getSelectedItemId())
        navigator.navigateBack()
    }


    fun onCreatorClicked(v : View){
        navigator.navigateToCreator()
    }




}