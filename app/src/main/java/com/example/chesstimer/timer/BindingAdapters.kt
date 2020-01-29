package com.example.chesstimer.timer

import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.chesstimer.R
import com.example.chesstimer.common.timer.GameState
import com.example.chesstimer.common.timer.TimerState

object BindingAdapters{
    @BindingAdapter("invertPrimaryTopBtnColor")
    @JvmStatic
    fun LinearLayout.invertPrimaryTopBtnColor(gameState: GameState?) {
        val context = context
        if(gameState != null)
        background = when(gameState) {
            GameState.NO_ONE -> context.getDrawable(R.color.colorDark)
            GameState.PLAYER_TOP -> context.getDrawable(R.drawable.timer_primary_btn)
            else -> context.getDrawable(R.color.colorDark)
        }
    }
    @BindingAdapter("invertPrimaryBottomBtnColor")
    @JvmStatic
    fun LinearLayout.invertPrimaryBottomBtnColor(gameState: GameState?) {
        val context = context
        if(gameState != null)
            background = when(gameState) {
                GameState.NO_ONE -> context.getDrawable(R.color.colorDark)
                GameState.PLAYER_BOTTOM -> context.getDrawable(R.drawable.timer_primary_btn)
                else -> context.getDrawable(R.color.colorDark)
            }
    }


    @BindingAdapter("invertSecondaryTopBtnColor")
    @JvmStatic
    fun TextView.invertSecondaryTopBtnColor(gameState: GameState?) {
        val context = context
        if(gameState != null)
            background = when(gameState) {
                GameState.NO_ONE -> context.getDrawable(R.color.colorDark)
                GameState.PLAYER_TOP ->  context.getDrawable(R.drawable.timer_secondary_btn)
                else -> context.getDrawable(R.color.colorDark)
            }

    }

    @BindingAdapter("invertSecondaryBottomBtnColor")
    @JvmStatic
    fun TextView.invertSecondaryBottomBtnColor(gameState: GameState?) {
        val context = context
        if(gameState != null)
            background = when(gameState) {
                GameState.NO_ONE -> context.getDrawable(R.color.colorDark)
                GameState.PLAYER_BOTTOM -> context.getDrawable(R.drawable.timer_secondary_btn)
                            else -> context.getDrawable(R.color.colorDark)
            }

    }

    @BindingAdapter("changeTopTextColor")
    @JvmStatic
    fun TextView.changeTopTextColor(gameState: GameState?) {
        val context = context
        if (gameState != null)
            setTextColor(
                when (gameState) {
                    GameState.NO_ONE ->ContextCompat.getColor(context, R.color.colorWhite)
                    GameState.PLAYER_TOP -> ContextCompat.getColor(context, R.color.colorDark)
                        else -> ContextCompat.getColor(context, R.color.colorWhite)
                }
            )
        }

    @BindingAdapter("changeBottomTextColor")
    @JvmStatic
    fun TextView.changeBottomTextColor(gameState: GameState?) {
        val context = context
        if (gameState != null)
            setTextColor(
                when (gameState) {
                    GameState.NO_ONE ->ContextCompat.getColor(context, R.color.colorWhite)
                    GameState.PLAYER_BOTTOM -> ContextCompat.getColor(context, R.color.colorDark)
                    else -> ContextCompat.getColor(context, R.color.colorWhite)
                }
            )
        }
}