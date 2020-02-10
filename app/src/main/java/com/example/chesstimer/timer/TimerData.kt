package com.example.chesstimer.timer

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.example.chesstimer.R
import com.example.chesstimer.common.timer.GameState
import com.example.chesstimer.common.timer.TimerState

data class TimerData(var gameState: GameState = GameState.NO_ONE,
                     var timerState: TimerState = TimerState.PAUSED,
                     var maxTimeCountMillis: Long = 40000L){



    fun getInvertPrimaryTopBtnColor(context : Context) : Drawable? {
            return when (gameState) {
                GameState.NO_ONE -> context.getDrawable(R.color.colorDark)
                GameState.PLAYER_TOP -> context.getDrawable(R.drawable.timer_primary_btn)
                else -> context.getDrawable(R.color.colorDark)
            }
    }

    fun getInvertPrimaryBottomBtnColor(context : Context) : Drawable? {
            return when (gameState) {
                GameState.NO_ONE -> context.getDrawable(R.color.colorDark)
                GameState.PLAYER_BOTTOM -> context.getDrawable(R.drawable.timer_primary_btn)
                else -> context.getDrawable(R.color.colorDark)
            }
    }

    fun getInvertSecondaryTopBtnColor(context : Context) : Drawable? {
           return when (gameState) {
                GameState.NO_ONE -> context.getDrawable(R.color.colorDark)
                GameState.PLAYER_TOP -> context.getDrawable(R.drawable.timer_secondary_btn)
                else -> context.getDrawable(R.color.colorDark)
            }
    }
    fun getInvertSecondaryBottomBtnColor(context : Context) : Drawable? {

            return when (gameState) {
                GameState.NO_ONE -> context.getDrawable(R.color.colorDark)
                GameState.PLAYER_BOTTOM -> context.getDrawable(R.drawable.timer_secondary_btn)
                else -> context.getDrawable(R.color.colorDark)
            }

    }

    fun getTopTextColor(context : Context) : Int {
            return when (gameState) {
                GameState.NO_ONE ->  ContextCompat.getColor(context, R.color.colorWhite)
                GameState.PLAYER_TOP -> ContextCompat.getColor(context, R.color.colorDark)
                else -> ContextCompat.getColor(context, R.color.colorWhite)
            }


    }
    fun getBottomTextColor(context : Context) : Int {
           return when (gameState) {
                GameState.NO_ONE ->  ContextCompat.getColor(context, R.color.colorWhite)
                GameState.PLAYER_BOTTOM -> ContextCompat.getColor(context, R.color.colorDark)
                else -> ContextCompat.getColor(context, R.color.colorWhite)
            }
    }
}







