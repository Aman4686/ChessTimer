package com.example.chesstimer.common

object TimerUtils {

    fun millisToSeconds(millis: Long) = (millis / 1000).toInt()
    fun secondsToMillis(second: Int) = (second * 1000).toLong()
}