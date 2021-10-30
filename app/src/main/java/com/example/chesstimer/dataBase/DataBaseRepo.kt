package com.example.chesstimer.dataBase

import com.example.chesstimer.dataBase.dao.SettingEntity
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DataBaseRepo @Inject constructor(val dataBase : AppDataBase){

    fun getAllSetings(): Flowable<List<SettingEntity>> {
        return dataBase.settingDAO().getAll()
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getSettingById(id: Int): Single<SettingEntity> {
        return dataBase.settingDAO().getSettingById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun insertSetting(settingEntity: SettingEntity) {
        Completable.fromCallable {
            dataBase.settingDAO().insert(settingEntity)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun updateSetting(settingEntity: SettingEntity) {
        Completable.fromAction{
            dataBase.settingDAO().update(settingEntity)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

}