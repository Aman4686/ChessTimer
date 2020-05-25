package com.example.chesstimer.dataBase

import androidx.room.*
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface TemporaryDAO {

    @Query("SELECT * FROM temporaryentity WHERE id = 1")
    fun getTemporary() : Single<TemporaryEntity>

    @Query("SELECT * FROM temporaryentity")
    fun getAllTemporary() : Observable<List<TemporaryEntity>>

    @Insert
    fun insert(temporaryEntity: TemporaryEntity)

    @Update
    fun update(temporaryEntity: TemporaryEntity)



}