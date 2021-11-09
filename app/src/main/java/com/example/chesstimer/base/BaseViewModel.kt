package com.example.chesstimer.base


import androidx.lifecycle.*
import com.example.chesstimer.common.navigation.TimerNavigator
import com.example.chesstimer.dataBase.SettingRepo
import com.example.chesstimer.features.creator.CreatorViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

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