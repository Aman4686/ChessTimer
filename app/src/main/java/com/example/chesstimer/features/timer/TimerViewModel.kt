package com.example.chesstimer.features.timer

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chesstimer.R
import com.example.chesstimer.base.BaseViewModel
import com.example.chesstimer.common.PrefUtils
import com.example.chesstimer.common.navigation.TimerNavigator
import com.example.chesstimer.common.states.GameTurnState
import com.example.chesstimer.common.states.TimerState
import com.example.chesstimer.dataBase.SettingRepo
import com.example.chesstimer.dataBase.dao.SettingEntity
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class TimerViewModel constructor(val navigator : TimerNavigator,
                                 val data : SettingRepo): BaseViewModel()  {


    val timerStateObserver = MutableLiveData(TimerStateObserver())
    val settingLiveData = MutableLiveData<SettingEntity>()

    val bottomPlayerTime = MutableLiveData<Long>()
    val topPlayerTime = MutableLiveData<Long>()

    init {
        initTime()
    }

    fun initTime(){
        val gameId = PrefUtils.getGameConfig()

        data.getSettingById(gameId).subscribeBy ({
            val mockedValue = SettingEntity(120000)
            data.insertSetting(mockedValue)

            PrefUtils.addGameConfig(gameId)
            settingLiveData.value = mockedValue
        },{
            settingLiveData.value = it
            initPlayersTime(it.timeDuration)
        })
    }

    private fun initPlayersTime(time : Long){
        bottomPlayerTime.value = time
        topPlayerTime.value = time
    }

    fun pausedTimer(){
        val timerState = timerStateObserver.value
        if(timerState != null && timerState.timerState != TimerState.FINISHED){
            timerState.timerState = TimerState.PAUSED
            timerState.gameTurnState = GameTurnState.NO_ONE
            timerStateObserver.value = timerState
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

    class TimerStateObserver(
        var gameTurnState: GameTurnState = GameTurnState.NO_ONE,
        var timerState: TimerState = TimerState.PAUSED){

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