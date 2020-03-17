package com.example.chesstimer.features.timer

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chesstimer.MainActivity
import com.example.chesstimer.R
import com.example.chesstimer.common.PrefUtils
import com.example.chesstimer.common.navigation.TimerNavigator
import com.example.chesstimer.common.states.GameTurnState
import com.example.chesstimer.common.states.TimerState
import com.example.chesstimer.dataBase.DataBaseRepo
import com.example.chesstimer.dataBase.SettingEntity
import com.example.chesstimer.dataBase.TemporaryEntity
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class TimerViewModel : ViewModel() {

    @Inject
    lateinit var navigator : TimerNavigator

    @Inject
    lateinit var data : DataBaseRepo

    val timerStateObserver = MutableLiveData(TimerStateObserver())
    val timers = CountDownTimers(this)

    init {
        MainActivity.appComponent.inject(this)
    }

    fun initTime(timer: CountDownTimers){
        data.getTemporary().subscribeBy ({
            data.insertTemporary(TemporaryEntity("fsdfs" , 120000))
            timer.gameTime = 120000
            timer.refreshTimers()
        },{
            timer.gameTime = it.time
            timer.refreshTimers()
        })
    }

    fun pausedTimer(){
        val timer = timerStateObserver.value
        if(timer != null && timer.timerState != TimerState.FINISHED){
            timer.timerState = TimerState.PAUSED
            timer.gameTurnState = GameTurnState.NO_ONE
            timerStateObserver.value = timer
        }
    }

    fun onPausedTimerClicked(v : View){
        pausedTimer()
    }

    fun onTopButtonClicked(v : View) {
        val timer = timerStateObserver.value
        if(timer != null) {
            val isNotFinished = timer.timerState != TimerState.FINISHED
            val isTopPlayerTurn = timer.gameTurnState != GameTurnState.PLAYER_BOTTOM
            if (isTopPlayerTurn && isNotFinished) {
                timer.timerState = TimerState.RUNNING
                timer.gameTurnState = GameTurnState.PLAYER_BOTTOM
                timerStateObserver.value = timer
            }
        }
    }

    fun onBottomButtonClicked(v : View) {
        val timer = timerStateObserver.value
        if (timer != null) {
            val isNotFinished = timer.timerState != TimerState.FINISHED
            val isBottomPlayerTurn = timer.gameTurnState != GameTurnState.PLAYER_TOP
            if (isBottomPlayerTurn && isNotFinished) {
                timer.timerState = TimerState.RUNNING
                timer.gameTurnState = GameTurnState.PLAYER_TOP
                timerStateObserver.value = timer
            }
        }
    }

    fun onResetTimerClicked(v : View){
        val timer = timerStateObserver.value
        if (timer != null) {
            timer.timerState = TimerState.RESETED
            timer.gameTurnState = GameTurnState.NO_ONE
            timerStateObserver.value = timer
        }
    }

    fun onSettingsClicked(v : View){
       navigator.navigateToCreator()
    }

    fun gameFinished(){
        val timer = timerStateObserver.value
        if (timer != null) {
            timer.timerState = TimerState.FINISHED
            timerStateObserver.value = timer
        }
    }


    class TimerStateObserver(var gameTurnState: GameTurnState = GameTurnState.NO_ONE,
                             var timerState: TimerState = TimerState.PAUSED){

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
                else -> context.getDrawable(R.color.transparent)
            }
        }

        fun getInvertSecondaryTopBtnColor(context : Context) : Drawable? {
            val color = getSecondaryBtnColor(timerState , context)
            return when (gameTurnState) {
                GameTurnState.PLAYER_TOP -> color
                else -> context.getDrawable(R.color.transparent)
            }
        }
        fun getInvertSecondaryBottomBtnColor(context : Context) : Drawable? {
            val color = getSecondaryBtnColor(timerState , context)
            return when (gameTurnState) {
                GameTurnState.PLAYER_BOTTOM -> color
                else -> context.getDrawable(R.color.transparent)
            }

        }

        fun getTopTextColor(context : Context) : Int {
            return when (gameTurnState) {
                GameTurnState.PLAYER_TOP -> ContextCompat.getColor(context, R.color.colorDark)
                else -> ContextCompat.getColor(context, R.color.colorGray)
            }


        }
        fun getBottomTextColor(context : Context) : Int {
            return when (gameTurnState) {
                GameTurnState.PLAYER_BOTTOM -> ContextCompat.getColor(context, R.color.colorDark)
                else -> ContextCompat.getColor(context, R.color.colorGray)
            }
        }
    }
}