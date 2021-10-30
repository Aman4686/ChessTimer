package com.example.chesstimer.features.settings

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

class ListViewModel constructor(val navigator : TimerNavigator,
                                val data : DataBaseRepo): BaseViewModel(){

    val settingListModel = MutableLiveData<List<SettingEntity>>()
    val adapter = ListRecyclerAdapter()

    init {
        adapter.checkedPosition = PrefUtils.getGameConfig()
        initList()
    }

    fun initList() {
       data.getAllSetings().subscribeBy {
            settingListModel.value = it
       }
    }

    fun onStartClicked(v : View){
        val index = adapter.getSelectedItemindex()
        PrefUtils.addGameConfig(index)
        navigator.navigateBack()
    }


    fun onCreatorClicked(v : View){
        navigator.navigateToCreator()
    }

    class Factory @Inject constructor(
        private val navigator: TimerNavigator,
        private val dataBaseRepo: DataBaseRepo
    ): ViewModelProvider.Factory{
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            require(modelClass == ListViewModel::class.java)
            return ListViewModel(navigator, dataBaseRepo) as T
        }
    }



}