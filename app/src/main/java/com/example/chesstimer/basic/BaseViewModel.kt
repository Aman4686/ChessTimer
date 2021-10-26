package com.example.chesstimer.basic


import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables

open class BaseViewModel : ViewModel() , LifecycleObserver {

    val compositeDisposable : CompositeDisposable = CompositeDisposable();

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroy(){
        compositeDisposable.clear()
    }

    fun addDisposable(disposable: Disposable){
        compositeDisposable.add(disposable)
    }


}