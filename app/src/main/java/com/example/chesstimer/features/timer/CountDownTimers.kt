package com.example.chesstimer.features.timer


import android.os.CountDownTimer
import android.util.Log

import com.example.chesstimer.common.TimerUtils
import com.example.chesstimer.common.states.GameTurnState

class CountDownTimers(private val model : TimerViewModel){

    var gameTime: Long = 60000

    private var bottomPlayerTimeLeft = gameTime
    private var topPlayerTimeLeft = gameTime

    private var timerTop : CountDownTimer? = null
    private var timerBottom : CountDownTimer? = null

    fun refreshState(){
        pausedTimers()
        bottomPlayerTimeLeft = gameTime
        topPlayerTimeLeft = gameTime
        prepareTimers()
        updateTimers()
    }

    fun refreshTimers(gameTime : Long){
        this.gameTime = gameTime
        pausedTimers()
        bottomPlayerTimeLeft = gameTime
        topPlayerTimeLeft = gameTime
        prepareTimers()
        updateTimers()
    }

    fun pausedTimers(){
        timerBottom?.cancel()
        timerTop?.cancel()
    }

    fun startTimer(gameTurnState: GameTurnState) {
        when(gameTurnState){
            GameTurnState.PLAYER_TOP -> {
                prepareTimerTopTimer()
                timerTop?.start()
                timerBottom?.cancel()
            } GameTurnState.PLAYER_BOTTOM -> {
                prepareTimerBottomTimer()
                timerBottom?.start()
                timerTop?.cancel()
            }
        }
    }

    private fun updateTopTimer(timeLeft: Long) {
        Log.d("testButonClick", "updateTopTimer:")
        model.topPlayerTime.value = timeLeft
    }

    private fun updateBottomTimer(timeLeft: Long) {
        Log.d("testButonClick", "updateBottomTimer:")
        model.bottomPlayerTime.value = timeLeft
    }

    private fun prepareTimers(){
        prepareTimerTopTimer()
        prepareTimerBottomTimer()
    }

    private fun updateTimers(){
        updateTopTimer(gameTime)
        updateBottomTimer(gameTime)
    }

    private fun prepareTimerTopTimer(){
        timerTop = object : CountDownTimer(topPlayerTimeLeft , TimerUtils.secondsToMillis(1)){
            override fun onFinish() {
                model.gameFinished()
            }

            override fun onTick(millisUntilFinished: Long) {
                topPlayerTimeLeft = millisUntilFinished
                updateTopTimer(millisUntilFinished)
            }
        }
    }

    private fun prepareTimerBottomTimer() {
        timerBottom = object : CountDownTimer(bottomPlayerTimeLeft, 1000) {
            override fun onFinish() {
                model.gameFinished()
            }

            override fun onTick(millisUntilFinished: Long) {
                bottomPlayerTimeLeft = millisUntilFinished
                updateBottomTimer(millisUntilFinished)
            }
        }
    }
}