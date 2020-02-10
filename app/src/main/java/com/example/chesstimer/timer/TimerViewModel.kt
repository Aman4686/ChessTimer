package com.example.chesstimer.timer

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chesstimer.common.navigation.TimerNavigator
import com.example.chesstimer.common.timer.GameState
import com.example.chesstimer.common.timer.TimerState
import javax.inject.Inject

class TimerViewModel @Inject constructor(var navigator: TimerNavigator) : ViewModel() {

    var timerDataModel : MutableLiveData<TimerData> = MutableLiveData(
        TimerData()
    )


    fun onTopButtonClicked(v : View) {
        val timer = timerDataModel.value
        if (timer != null && timer.gameState != GameState.PLAYER_BOTTOM) {
            timer.timerState = TimerState.RUNNING
            timer.gameState = GameState.PLAYER_BOTTOM
            timerDataModel.value = timer
        }
    }

    fun onBottomButtonClicked(v : View) {
        val timer = timerDataModel.value
        if (timer != null && timer.gameState != GameState.PLAYER_TOP) {
            timer.timerState = TimerState.RUNNING
            timer.gameState = GameState.PLAYER_TOP
            timerDataModel.value = timer
        }
    }

    fun onPausedTimerClicked(v : View){
        val timer = timerDataModel.value
        if (timer != null) {
            timer.timerState = TimerState.PAUSED
            timer.gameState = GameState.NO_ONE
            timerDataModel.value = timer
        }
    }

    fun onResetTimerClicked(v : View){
        val timer = timerDataModel.value
        if (timer != null) {
            timer.timerState = TimerState.RESETED
            timer.gameState = GameState.NO_ONE
            timerDataModel.value = timer
        }
    }


}