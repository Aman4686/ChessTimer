package com.example.chesstimer.dataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class TemporaryEntity(var title : String , var time : Long) {

    @PrimaryKey
    var id: Int = 1

    constructor(settingEntity: SettingEntity) : this(settingEntity.title , settingEntity.time)

}