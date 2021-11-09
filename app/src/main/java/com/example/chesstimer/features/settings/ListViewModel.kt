package com.example.chesstimer.features.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.chesstimer.base.BaseViewModel
import com.example.chesstimer.common.navigation.TimerNavigator
import com.example.chesstimer.dataBase.SettingRepo
import com.example.chesstimer.dataBase.dao.SettingEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

import javax.inject.Inject

class ListViewModel constructor(
    val navigator : TimerNavigator,
    val data : SettingRepo
): BaseViewModel(){

    val settingsListStateFlow = MutableStateFlow(listOf(SettingEntity(120000)))

    init {
        initList()
    }

    fun initList() {
        viewModelScope.launch {
            data.getAllSetings()
                .collect {
                settingsListStateFlow.emit(it)
            }
        }
    }

    fun insertSetting(){

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