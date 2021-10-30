package com.example.chesstimer.dataBase.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.chesstimer.common.Duration
import com.example.chesstimer.dataBase.dao.SettingEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
class SettingEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "timeDuration")
    var timeDuration : Long) {

    companion object {
        const val TABLE_NAME = "settings_entity_table"
    }

}