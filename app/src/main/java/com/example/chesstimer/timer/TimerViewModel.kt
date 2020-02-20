package com.example.chesstimer.timer

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chesstimer.App
import com.example.chesstimer.MainActivity
import com.example.chesstimer.common.navigation.TimerNavigator
import com.example.chesstimer.common.states.GameTurnState
import com.example.chesstimer.common.states.TimerState
import javax.inject.Inject

class TimerViewModel : ViewModel() {

    @Inject
    lateinit var navigator : TimerNavigator

    init {
        MainActivity.appComponent.inject(this)
    }

    val timerDataModel = MutableLiveData(TimerData())

    fun onTopButtonClicked(v : View) {
        val timer = timerDataModel.value
        if(timer != null) {
            val isNotFinished = timer.timerState != TimerState.FINISHED
            val isTopPlayerTurn = timer.gameTurnState != GameTurnState.PLAYER_BOTTOM
            if (isTopPlayerTurn && isNotFinished) {
                timer.timerState = TimerState.RUNNING
                timer.gameTurnState = GameTurnState.PLAYER_BOTTOM
                timerDataModel.value = timer
            }
        }
    }

    fun onBottomButtonClicked(v : View) {
        val timer = timerDataModel.value
        if(timer != null) {
            val isNotFinished = timer.timerState != TimerState.FINISHED
            val isBottomPlayerTurn = timer.gameTurnState != GameTurnState.PLAYER_TOP
            if (isBottomPlayerTurn && isNotFinished) {
                timer.timerState = TimerState.RUNNING
                timer.gameTurnState = GameTurnState.PLAYER_TOP
                timerDataModel.value = timer
            }
        }
    }

    fun onPausedTimerClicked(v : View){
        val timer = timerDataModel.value
        if (timer != null) {
            timer.timerState = TimerState.PAUSED
            timer.gameTurnState = GameTurnState.NO_ONE
            timerDataModel.value = timer
        }
    }

    fun onResetTimerClicked(v : View){
        val timer = timerDataModel.value
        if (timer != null) {
            timer.timerState = TimerState.RESETED
            timer.gameTurnState = GameTurnState.NO_ONE
            timerDataModel.value = timer
        }
    }

    fun gameFinished(){
        val timer = timerDataModel.value
        if (timer != null) {
            timer.timerState = TimerState.FINISHED
            timerDataModel.value = timer
        }
    }
}