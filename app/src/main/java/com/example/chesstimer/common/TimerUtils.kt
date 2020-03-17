package com.example.chesstimer.common

import java.util.*

object TimerUtils {

    fun millisToSeconds(millis: Long) = (millis / 1000 % 60).toInt()
    fun millisToMinutes(millis: Long) = (millis / 1000 / 60 % 60).toInt()
    fun millisToHours(millis: Long) = (millis / 3600000).toInt()



    fun hoursToMillis(hours: Int) = (hours * 3600000).toLong()
    fun minutesToMillis(minutes: Int) = (minutes * 60 * 1000).toLong()
    fun secondsToMillis(seconds: Int) = (seconds * 1000).toLong()


    fun getFormattedTime(time: Long) : String {
        val seconds : Int = millisToSeconds(time)
        val minutes : Int = millisToMinutes(time)
        val hours : Int = millisToHours(time)

        return if(time >= 3600000)
             String.format(Locale.getDefault() ,"%02d:%02d:%02d" ,hours,  minutes , seconds)
        else
             String.format(Locale.getDefault() ,"%02d:%02d" , minutes , seconds)
    }

    fun getFormattedTime(hours : Int, minutes : Int, seconds : Int) : String {
        return if(hours >= 1)
            String.format(Locale.getDefault() ,"%02d:%02d:%02d" ,hours,  minutes , seconds)
        else
            String.format(Locale.getDefault() ,"%02d:%02d" , minutes , seconds)
    }

}