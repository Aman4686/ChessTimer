package com.example.chesstimer.dataBase

import com.example.chesstimer.App
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DataBaseRepo {

    fun getAll() : Single<List<SettingEntity>> {
        return App.db.settingDAO().getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getSettingById(id : Int) : Single<SettingEntity> {
        return App.db.settingDAO().getSettingById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun insert(settingEntity: SettingEntity) : Completable {
        return Completable.fromAction {
            App.db.settingDAO().insert(settingEntity)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    }

}