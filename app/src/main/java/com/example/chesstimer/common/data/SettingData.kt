package com.example.chesstimer.common.data

import com.example.chesstimer.dataBase.dao.SettingEntity

class SettingData {
    val id : Int
    val time : Long

    constructor(settingEntity: SettingEntity){
        this.id = settingEntity.id?:0
        this.time = settingEntity.timeDuration
    }



}