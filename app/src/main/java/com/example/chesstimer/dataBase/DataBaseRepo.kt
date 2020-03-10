package com.example.chesstimer.dataBase

import android.util.Log
import com.example.chesstimer.App
import com.example.chesstimer.data.SettingData
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlin.math.log

class DataBaseRepo {

    fun getAll() : Flowable<List<SettingData>> {
        return App.db.settingDAO().getAll().flatMap {
            val list = it.map {
                SettingData(it)
            }
            Flowable.just(list)
        }.observeOn(AndroidSchedulers.mainThread())
    }

    fun getSettingById(id : Int) : Single<SettingData> {
        return App.db.settingDAO().getSettingById(id)
            .flatMap {
                Single.just(SettingData(it))
            }
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