package com.example.chesstimer.timer

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chesstimer.R
import com.example.chesstimer.data.Timer
import com.example.chesstimer.common.navigation.TimerNavigator
import com.example.chesstimer.common.timer.GameState
import com.example.chesstimer.common.timer.TimerState

class TimerViewModel(var navigator: TimerNavigator) : ViewModel() {

    var timerModel : MutableLiveData<Timer> = MutableLiveData(Timer())


    fun onTopButtonClicked(v : View) {
        val timer = timerModel.value
        if (timer != null && timer.gameState != GameState.PLAYER_TOP) {
            timer.timerState = TimerState.RUNNING
            timer.gameState = GameState.PLAYER_TOP
            timerModel.value = timer
        }
    }

    fun onBottomButtonClicked(v : View) {
        val timer = timerModel.value
        if (timer != null && timer.gameState != GameState.PLAYER_BOTTOM) {
            timer.timerState = TimerState.RUNNING
            timer.gameState = GameState.PLAYER_BOTTOM
            timerModel.value = timer
        }
    }

    fun onPausedTimerClicked(v : View){
        val timer = timerModel.value
        if (timer != null) {
            timer.timerState = TimerState.PAUSED
            timer.gameState = GameState.NO_ONE
            timerModel.value = timer
        }
    }

    fun onResetTimerClicked(v : View){
        val timer = timerModel.value
        if (timer != null) {
            timer.timerState = TimerState.RESETED
            timer.gameState = GameState.NO_ONE
            timerModel.value = timer
        }
    }

    fun getInvertPrimaryTopBtnColor(context :Context) : Drawable? {
        val timerModel = timerModel.value
        return if (timerModel != null)
            when (timerModel.gameState) {
                GameState.NO_ONE -> context.getDrawable(R.color.colorDark)
                GameState.PLAYER_TOP -> context.getDrawable(R.drawable.timer_primary_btn)
                else -> context.getDrawable(R.color.colorDark)
            }
        else
            null

    }

    fun getInvertPrimaryBottomBtnColor(context :Context) : Drawable? {
        val timerModel = timerModel.value
        return if (timerModel != null)
            when (timerModel.gameState) {
                GameState.NO_ONE -> context.getDrawable(R.color.colorDark)
                GameState.PLAYER_BOTTOM -> context.getDrawable(R.drawable.timer_primary_btn)
                else -> context.getDrawable(R.color.colorDark)
            }
        else
            null

    }

    fun getInvertSecondaryTopBtnColor(context :Context) : Drawable? {
        val timerModel = timerModel.value
        return if (timerModel != null)
            when (timerModel.gameState) {
                GameState.NO_ONE -> context.getDrawable(R.color.colorDark)
                GameState.PLAYER_TOP -> context.getDrawable(R.drawable.timer_secondary_btn)
                else -> context.getDrawable(R.color.colorDark)
            }
        else
            null

    }
    fun getInvertSecondaryBottomBtnColor(context :Context) : Drawable? {
        val timerModel = timerModel.value
        return if (timerModel != null)
            when (timerModel.gameState) {
                GameState.NO_ONE -> context.getDrawable(R.color.colorDark)
                GameState.PLAYER_BOTTOM -> context.getDrawable(R.drawable.timer_secondary_btn)
                else -> context.getDrawable(R.color.colorDark)
            }
        else
            null

    }

    fun getTopTextColor(context :Context) : Int {
        val timerModel = timerModel.value
        return if (timerModel != null)
            when (timerModel.gameState) {
                GameState.NO_ONE ->  ContextCompat.getColor(context, R.color.colorWhite)
                GameState.PLAYER_TOP -> ContextCompat.getColor(context, R.color.colorDark)
                else -> ContextCompat.getColor(context, R.color.colorWhite)
            }
        else
            0

    }
    fun getBottomTextColor(context :Context) : Int {
        val timerModel = timerModel.value
        return if (timerModel != null)
            when (timerModel.gameState) {
                GameState.NO_ONE ->  ContextCompat.getColor(context, R.color.colorWhite)
                GameState.PLAYER_BOTTOM -> ContextCompat.getColor(context, R.color.colorDark)
                else -> ContextCompat.getColor(context, R.color.colorWhite)
            }
        else
            0

    }

}