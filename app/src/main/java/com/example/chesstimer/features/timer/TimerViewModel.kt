package com.example.chesstimer.features.timer

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.chesstimer.R
import com.example.chesstimer.common.PrefUtils
import com.example.chesstimer.common.navigation.TimerNavigator
import com.example.chesstimer.common.states.GameTurnState
import com.example.chesstimer.common.states.TimerState
import com.example.chesstimer.dataBase.SettingRepo
import com.example.chesstimer.dataBase.dao.SettingEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class TimerViewModel constructor(val navigator : TimerNavigator,
                                 val data : SettingRepo): ViewModel()  {

    val timerStateObserver = MutableStateFlow(TimerStateObserver())
    val settingStateFlow = MutableStateFlow(SettingEntity(120000))

    val bottomPlayerTime = MutableStateFlow(0L)
    val topPlayerTime = MutableStateFlow(0L)

    init {
        initTime()
    }

    fun initTime(){
        val gameId = PrefUtils.getGameConfig()

        viewModelScope.launch(Dispatchers.IO) {
            data.getSettingById(gameId)
                .collect{
                    settingStateFlow.emit(SettingEntity(120000))
                }
        }
    }

    fun initPlayersTime(time : Long){
        bottomPlayerTime.value = time
        topPlayerTime.value = time
    }

    fun pausedTimer(){
        val timer = timerStateObserver.value.copy()
        if(timer.isNotFinished){
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
            if (timer.isTopPlayerTurn && timer.isNotFinished) {
                val result = TimerStateObserver(GameTurnState.PLAYER_BOTTOM , TimerState.RUNNING)
                timerStateObserver.value = result
            }
    }

    fun onBottomButtonClicked(v : View) {
        val timer = timerStateObserver.value
            if (timer.isBottomPlayerTurn && timer.isNotFinished) {
                val result = TimerStateObserver(GameTurnState.PLAYER_TOP , TimerState.RUNNING)
                timerStateObserver.value = result
            }
    }

    fun onResetTimerClicked(v : View){
        val timer = timerStateObserver.value.copy(
            gameTurnState = GameTurnState.NO_ONE,
            timerState = TimerState.RESETED
        )
            timerStateObserver.value = timer
    }

    fun onSettingsClicked(v : View){
       navigator.navigateToCreator()

    }

    fun gameFinished(){
        val timer = timerStateObserver.value.copy(timerState = TimerState.FINISHED)
            timerStateObserver.value = timer
    }

    class Factory @Inject constructor(
        private val navigator: TimerNavigator,
        private val settingRepo: SettingRepo
    ): ViewModelProvider.Factory{
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            require(modelClass == TimerViewModel::class.java)
            return TimerViewModel(navigator, settingRepo) as T
        }
    }

  data class TimerStateObserver(
        var gameTurnState: GameTurnState = GameTurnState.NO_ONE,
        var timerState: TimerState = TimerState.PAUSED){

        val isNotFinished get() = timerState != TimerState.FINISHED
        val isBottomPlayerTurn get() = gameTurnState != GameTurnState.PLAYER_TOP
        val isTopPlayerTurn get() = gameTurnState != GameTurnState.PLAYER_BOTTOM

        private fun getPrimaryBtnColor(timerState: TimerState , context: Context) : Drawable?{
            return if(timerState == TimerState.FINISHED){
                context.getDrawable(R.drawable.timer_primary_finished_btn)
            }else{
                context.getDrawable(R.drawable.timer_primary_btn)
            }
        }

        private fun getSecondaryBtnColor(timerState: TimerState, context: Context) : Drawable?{
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