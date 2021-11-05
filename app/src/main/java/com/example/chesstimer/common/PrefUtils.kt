package com.example.chesstimer.common

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor

object PrefUtils {

    private lateinit var sPref : SharedPreferences
    private lateinit var editor: Editor

    fun init(context: Context){
        sPref = context.getSharedPreferences("MY_STORAGE" , MODE_PRIVATE)
        editor = sPref.edit()
    }

    fun setGameConfig(selectedItemId : Int){
        editor.putInt("PREF_ID_KEY", selectedItemId)
        editor.commit()
    }

    fun getGameConfig() : Int{
        return sPref.getInt("PREF_ID_KEY" , 1)
    }
}