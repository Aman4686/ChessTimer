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

    fun getAllSetings(): Flowable<List<SettingEntity>> {
        return App.db.settingDAO().getAll()
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getSettingById(id: Int): Single<SettingEntity> {
        return App.db.settingDAO().getSettingById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun insertSetting(settingEntity: SettingEntity) {
        Completable.fromCallable {
            App.db.settingDAO().insert(settingEntity)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun getTemporary(): Single<TemporaryEntity> {
        return App.db.temporaryDAO().getTemporary()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun insertTemporary(temporaryEntity: TemporaryEntity) {
        Completable.fromCallable {
            App.db.temporaryDAO().insert(temporaryEntity)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun updateTemporary(temporaryEntity: TemporaryEntity) {
        Completable.fromCallable {
            App.db.temporaryDAO().update(temporaryEntity)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

}