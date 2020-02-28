package com.example.chesstimer.dataBase

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
class SettingEntity(var title : String , var time : Long) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}