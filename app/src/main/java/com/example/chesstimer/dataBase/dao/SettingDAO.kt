package com.example.chesstimer.dataBase.dao

import androidx.room.*
import io.reactivex.Flowable
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow

@Dao
interface SettingDAO {

    @Query("SELECT * FROM ${SettingEntity.TABLE_NAME}")
    fun getAll() : Flow<List<SettingEntity>>

    @Query("SELECT * FROM ${SettingEntity.TABLE_NAME} WHERE :id like id")
    fun getSettingById(id : Int) : Flow<SettingEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(setting: SettingEntity)

    @Update
    suspend fun update(settingEntity: SettingEntity)

}