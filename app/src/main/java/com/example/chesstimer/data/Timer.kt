package com.example.chesstimer.data

import com.example.chesstimer.common.timer.GameState
import com.example.chesstimer.common.timer.TimerState

data class Timer(var gameState: GameState = GameState.NO_ONE,
                 var timerState: TimerState = TimerState.PAUSED,
                 var maxTimeCountMillis: Long = 40000L)







