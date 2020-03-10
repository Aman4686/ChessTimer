package com.example.chesstimer.common

import java.util.*

object TimerUtils {

    fun millisToSeconds(millis: Long) = (millis / 1000 % 60).toInt()
    fun secondsToMillis(second: Int) = (second * 1000).toLong()
    fun millisToMinutes(millis: Long) = (millis / 1000 / 60).toInt()


    fun getTimeLeftFormatted(timeLeft: Long) : String {
        val minutes : Int = millisToMinutes(timeLeft)
        val seconds : Int = millisToSeconds(timeLeft)
        return String.format(Locale.getDefault() ,"%01d:%02d" , minutes , seconds)
    }
}