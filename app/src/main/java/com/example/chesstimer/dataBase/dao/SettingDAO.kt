package com.example.chesstimer.dataBase.dao

import androidx.room.*
import io.reactivex.Flowable
import io.reactivex.Single
@Dao
interface SettingDAO {

    @Query("SELECT * FROM ${SettingEntity.TABLE_NAME}")
    fun getAll() : Flowable<List<SettingEntity>>

    @Query("SELECT * FROM ${SettingEntity.TABLE_NAME} WHERE :id like id")
    fun getSettingById(id : Int) : Single<SettingEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(setting: SettingEntity)


    fun update(settingEntity: SettingEntity)

}