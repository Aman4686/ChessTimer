package com.example.chesstimer.common

import java.util.*

object TimerUtils {

    fun secondsToMillis(seconds: Int) = (seconds * 1000).toLong()

    fun millisToSeconds(millis: Long) = (millis / 1000 % 60).toInt()
    fun millisToMinutes(millis: Long) = (millis / 1000 / 60).toInt()
    fun millisToHours(millis: Long) = (millis / 3600000).toInt()

    fun hoursToMillis(hours: Long) = (hours * 3600000).toLong()


    fun getTimeLeftFormatted(timeLeft: Long) : String {
        val minutes : Int = millisToMinutes(timeLeft)
        val seconds : Int = millisToSeconds(timeLeft)
        val hours : Int = millisToHours(timeLeft)

        return if(timeLeft >= 3600000)
             String.format(Locale.getDefault() ,"%02d:%02d:%02d" ,hours,  minutes , seconds)
        else
             String.format(Locale.getDefault() ,"%02d:%02d" , minutes , seconds)
    }
}