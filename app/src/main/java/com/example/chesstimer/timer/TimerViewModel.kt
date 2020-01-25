package com.example.chesstimer.timer

import android.view.View
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chesstimer.R
import com.example.chesstimer.common.navigation.TimerNavigator

class TimerViewModel(val navController : TimerNavigator) : ViewModel() {
    val model = MutableLiveData<Boolean>()

    fun onTopButtonClicked(v : View) {
        model.value = true
    }

    fun onBottomButtonClicked(v : View) {
        model.value = false
    }



}