package com.example.chesstimer.features.timer

import android.util.Log
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import io.reactivex.functions.Predicate
import java.util.concurrent.TimeUnit

class RxTimer(private val model : TimerViewModel) {

    fun startTimer(){
        Observable.interval(1, TimeUnit.SECONDS)
            .takeWhile{it <= 6}
            .subscribe( {
                   Log.d("RxTimer", "initTimer: " + it);
            },{

            },{
                Log.d("RxTimer", "Finish");
            })
    }



}