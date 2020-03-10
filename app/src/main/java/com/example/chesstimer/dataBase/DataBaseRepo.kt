package com.example.chesstimer.dataBase

import android.util.Log
import com.example.chesstimer.App
import com.example.chesstimer.data.SettingData
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlin.math.log

class DataBaseRepo {

    fun getAll() : Single<List<SettingData>> {
        return App.db.settingDAO().getAll()
            .toObservable()
            .flatMap {
                Observable.fromIterable(it).flatMap{
                    val result = SettingData(it)
                    return@flatMap Observable.just(result)
            }}
            .toList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getSettingById(id : Int) : Single<SettingEntity> {
        return App.db.settingDAO().getSettingById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun insert(settingEntity: SettingEntity) {
         Completable.fromCallable {
            App.db.settingDAO().insert(settingEntity)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
             .subscribe()
    }

}