package com.example.chesstimer.timer

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chesstimer.common.Timer
import com.example.chesstimer.common.navigation.TimerNavigator
import com.example.chesstimer.common.timer.GameState

class TimerViewModel(val navController : TimerNavigator) : ViewModel() {
    var timerModel : MutableLiveData<Timer> = MutableLiveData(Timer())


    fun onTopButtonClicked(v : View) {
        val timer = timerModel.value
        if (timer != null && timer.gameState != GameState.PLAYER_TOP) {
            timer.gameState = GameState.PLAYER_TOP
            timerModel.value = timer
        }
    }

    fun onBottomButtonClicked(v : View) {
        val timer = timerModel.value
        if (timer != null && timer.gameState != GameState.PLAYER_BOTTOM) {
            timer.gameState = GameState.PLAYER_BOTTOM
            timerModel.value = timer
        }
    }



}