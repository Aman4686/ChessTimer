package com.example.chesstimer.timer

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.example.chesstimer.R
import com.example.chesstimer.common.states.GameTurnState
import com.example.chesstimer.common.states.TimerState

data class TimerData(var gameTurnState: GameTurnState = GameTurnState.NO_ONE,
                     var timerState: TimerState = TimerState.PAUSED,
                     var maxTimeCountMillis: Long = 500000L){

    private fun getPrimaryBtnColor(timerState: TimerState , context: Context) : Drawable?{
        return if(timerState == TimerState.FINISHED){
            context.getDrawable(R.drawable.timer_primary_finished_btn)
        }else{
            context.getDrawable(R.drawable.timer_primary_btn)
        }
    }

    private fun getSecondaryBtnColor(timerState: TimerState , context: Context) : Drawable?{
        return if(timerState == TimerState.FINISHED){
            context.getDrawable(R.drawable.timer_secondary_finished_btn)
        }else{
            context.getDrawable(R.drawable.timer_secondary_btn)
        }
    }

    fun getInvertPrimaryTopBtnColor(context : Context) : Drawable? {
            val color = getPrimaryBtnColor(timerState , context)
            return when (gameTurnState) {
                GameTurnState.PLAYER_TOP -> color
                else -> context.getDrawable(R.color.transparent)
            }
    }

    fun getInvertPrimaryBottomBtnColor(context : Context) : Drawable? {
        val color = getPrimaryBtnColor(timerState , context)
            return when (gameTurnState) {
                GameTurnState.PLAYER_BOTTOM -> color
                else -> context.getDrawable(R.color.colorDark)
            }
    }

    fun getInvertSecondaryTopBtnColor(context : Context) : Drawable? {
        val color = getSecondaryBtnColor(timerState , context)
           return when (gameTurnState) {
                GameTurnState.PLAYER_TOP -> color
                else -> context.getDrawable(R.color.colorDark)
            }
    }
    fun getInvertSecondaryBottomBtnColor(context : Context) : Drawable? {
        val color = getSecondaryBtnColor(timerState , context)
            return when (gameTurnState) {
                GameTurnState.PLAYER_BOTTOM -> color
                else -> context.getDrawable(R.color.colorDark)
            }

    }

    fun getTopTextColor(context : Context) : Int {
            return when (gameTurnState) {
                GameTurnState.PLAYER_TOP -> ContextCompat.getColor(context, R.color.colorDark)
                else -> ContextCompat.getColor(context, R.color.colorWhite)
            }


    }
    fun getBottomTextColor(context : Context) : Int {
           return when (gameTurnState) {
                GameTurnState.PLAYER_BOTTOM -> ContextCompat.getColor(context, R.color.colorDark)
                else -> ContextCompat.getColor(context, R.color.colorWhite)
            }
    }
}







