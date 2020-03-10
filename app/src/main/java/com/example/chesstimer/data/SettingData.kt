package com.example.chesstimer.data

import com.example.chesstimer.dataBase.SettingEntity

class SettingData {

    val id : Int
    val title : String
    val time : Long

    constructor(settingEntity: SettingEntity){
        this.id = settingEntity.id
        this.title = settingEntity.title
        this.time = settingEntity.time
    }


}