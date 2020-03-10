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

    fun getAll() : Flowable<List<SettingEntity>> {
        return App.db.settingDAO().getAll()
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