package com.example.chesstimer.timer

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chesstimer.common.navigation.TimerNavigator
import com.example.chesstimer.common.timer.GameState
import com.example.chesstimer.common.timer.TimerState

class TimerViewModel(var navigator: TimerNavigator) : ViewModel() {

    var timerLiveDataModel : MutableLiveData<TimerLiveData> = MutableLiveData(
        TimerLiveData()
    )


    fun onTopButtonClicked(v : View) {
        val timer = timerLiveDataModel.value
        if (timer != null && timer.gameState != GameState.PLAYER_BOTTOM) {
            timer.timerState = TimerState.RUNNING
            timer.gameState = GameState.PLAYER_BOTTOM
            timerLiveDataModel.value = timer
        }
    }

    fun onBottomButtonClicked(v : View) {
        val timer = timerLiveDataModel.value
        if (timer != null && timer.gameState != GameState.PLAYER_TOP) {
            timer.timerState = TimerState.RUNNING
            timer.gameState = GameState.PLAYER_TOP
            timerLiveDataModel.value = timer
        }
    }

    fun onPausedTimerClicked(v : View){
        val timer = timerLiveDataModel.value
        if (timer != null) {
            timer.timerState = TimerState.PAUSED
            timer.gameState = GameState.NO_ONE
            timerLiveDataModel.value = timer
        }
    }

    fun onResetTimerClicked(v : View){
        val timer = timerLiveDataModel.value
        if (timer != null) {
            timer.timerState = TimerState.RESETED
            timer.gameState = GameState.NO_ONE
            timerLiveDataModel.value = timer
        }
    }


}