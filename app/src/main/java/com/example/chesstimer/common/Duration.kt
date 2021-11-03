package com.example.chesstimer.common

data class Duration(val timeMillis: Long) {

    val hours = TimerUtils.millisToHours(timeMillis)
    val seconds = TimerUtils.millisToSeconds(timeMillis)
    val minutes = TimerUtils.millisToMinutes(timeMillis)

}