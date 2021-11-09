package com.example.chesstimer.dataBase

import com.example.chesstimer.AppDataBase
import com.example.chesstimer.dataBase.dao.SettingEntity
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SettingRepo @Inject constructor(val dataBase : AppDataBase){

    fun getAllSetings(): Flow<List<SettingEntity>> {
        return dataBase.settingDAO().getAll()
    }

    fun getSettingById(id: Int): Flow<SettingEntity> {
        return dataBase.settingDAO().getSettingById(id)

    }

    suspend fun insertSetting(settingEntity: SettingEntity) {
        dataBase.settingDAO().insert(settingEntity)
    }

    suspend fun updateSetting(settingEntity: SettingEntity) {
            dataBase.settingDAO().update(settingEntity)
    }

}