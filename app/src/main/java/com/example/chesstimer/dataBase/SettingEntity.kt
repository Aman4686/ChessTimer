package com.example.chesstimer.dataBase

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.chesstimer.common.Duration

@Entity
class SettingEntity(var title : String , var timeDuration : Long) {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

}