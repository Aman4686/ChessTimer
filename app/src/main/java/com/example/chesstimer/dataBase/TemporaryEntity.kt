package com.example.chesstimer.dataBase

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.chesstimer.common.Duration

@Entity
class TemporaryEntity(var title : String , var timeDuration : Long) {

    @PrimaryKey
    var id: Int = 1

    constructor(settingEntity: SettingEntity) : this(settingEntity.title , settingEntity.timeDuration)

}