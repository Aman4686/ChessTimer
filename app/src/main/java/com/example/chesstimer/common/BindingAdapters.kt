package com.example.chesstimer.common

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.chesstimer.common.TimerUtils

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("android:formattedTime")
    fun TextView.formattedTime(time : Long){
        text = TimerUtils.getFormattedTime(time)
    }

}