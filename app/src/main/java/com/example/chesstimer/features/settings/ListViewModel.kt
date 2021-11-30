package com.example.chesstimer.features.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chesstimer.common.navigation.TimerNavigator
import com.example.chesstimer.dataBase.SettingRepo
import com.example.chesstimer.dataBase.dao.SettingEntity
import kotlinx.coroutines.flow.MutableStateFlow

import javax.inject.Inject

class ListViewModel constructor(
    val navigator : TimerNavigator,
    val data : SettingRepo
): ViewModel(){

    val settingsListStateFlow = MutableStateFlow(listOf(SettingEntity(120000)))

    init {
        initList()
    }

    fun initList() {
//        viewModelScope.launch {
//            data.getAllSetings()
//                .collect {
//              //  settingsListStateFlow.emit(it)
//            }
//        }
    }

    class Factory @Inject constructor(
        private val navigator: TimerNavigator,
        private val settingRepo: SettingRepo
    ): ViewModelProvider.Factory{
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            require(modelClass == ListViewModel::class.java)
            return ListViewModel(navigator, settingRepo) as T
        }
    }





}