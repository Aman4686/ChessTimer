package com.example.chesstimer.common

import com.example.chesstimer.common.timer.GameState
import com.example.chesstimer.common.timer.TimerState

data class Timer(var gameState: GameState = GameState.PLAYER_TOP, var timerState: TimerState = TimerState.PAUSED)
