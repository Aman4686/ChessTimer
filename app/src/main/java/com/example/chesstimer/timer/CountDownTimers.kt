package com.example.chesstimer.timer


import android.os.CountDownTimer
import android.widget.TextView

import com.example.chesstimer.common.TimerUtils
import com.example.chesstimer.common.states.GameTurnState

class CountDownTimers(var gameTime: Long,
                      private val bottomPrimaryTimer : TextView,
                      private val topPrimaryTimer : TextView,
                      private val bottomSecondaryTimer : TextView,
                      private val topSecondaryTimer : TextView,
                      private val model : TimerViewModel){

    private var bottomPlayerTimeLeft = gameTime
    private var topPlayerTimeLeft = gameTime

    private var timerTop : CountDownTimer? = null
    private var timerBottom : CountDownTimer? = null

    init {
        prepareTimers()
        updateTimers()
    }

    fun resetTimers(){
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
        val timeLeftFormatted = TimerUtils.getTimeLeftFormatted(timeLeft)
        topPrimaryTimer.text = timeLeftFormatted
        bottomSecondaryTimer.text = timeLeftFormatted
    }

    private fun updateBottomTimer(timeLeft: Long) {
        val timeLeftFormatted = TimerUtils.getTimeLeftFormatted(timeLeft)
        bottomPrimaryTimer.text = timeLeftFormatted
        topSecondaryTimer.text = timeLeftFormatted
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