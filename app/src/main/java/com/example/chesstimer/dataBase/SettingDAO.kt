package com.example.chesstimer.dataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Single
@Dao
interface SettingDAO {

    @Query("SELECT * FROM settingentity")
    fun getAll() : Single<List<SettingEntity>>

    @Query("SELECT * FROM settingentity WHERE id = :id")
    fun getSettingById(id : Int) : Single<SettingEntity>

    @Insert
    fun insert(settting: SettingEntity)



}