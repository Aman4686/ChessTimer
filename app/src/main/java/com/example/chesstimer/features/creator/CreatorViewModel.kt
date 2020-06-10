package com.example.chesstimer.features.creator

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chesstimer.MainActivity
import com.example.chesstimer.common.navigation.TimerNavigator
import com.example.chesstimer.dataBase.DataBaseRepo
import com.example.chesstimer.dataBase.SettingEntity
import com.example.chesstimer.dataBase.TemporaryEntity
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class CreatorViewModel : ViewModel() {

    @Inject
    lateinit var navigator : TimerNavigator

    @Inject
    lateinit var data : DataBaseRepo

    val temporaryLiveData = MutableLiveData<TemporaryEntity>()

    init {
        MainActivity.appComponent.inject(this)
        initCreator()
    }

    fun onSaveClicked(title : String, time : Long){
        val temporary = temporaryLiveData.value
        if(temporary != null){
            temporary.title = title
            temporary.timeDuration = time
            data.updateTemporary(temporary)
            navigator.navigateBack()
        }
    }

    fun onAddToListClicked(title : String , time : Long){
        data.insertSetting(SettingEntity(title, time))
    }

    fun onBackClicked(v : View){
        // data.insertTemporary(SettingEntity(title , duration))
        navigator.navigateBack()
    }

    fun onListClicked(v : View){
     navigator.navigateToSettingsList()
    }

    fun initCreator(){
        data.getTemporary().subscribeBy{
            temporaryLiveData.value = it
        }
    }



}