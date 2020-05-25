package com.example.chesstimer.common

class Duration(var timeMillis: Long) {

    val hours = TimerUtils.millisToHours(timeMillis)
    val seconds = TimerUtils.millisToSeconds(timeMillis)
    val minutes = TimerUtils.millisToMinutes(timeMillis)

}