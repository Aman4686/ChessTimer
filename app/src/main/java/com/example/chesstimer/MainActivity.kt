package com.example.chesstimer

import android.os.Bundle
import com.example.chesstimer.basic.BaseActivity


class MainActivity : BaseActivity() {

    companion object{
        lateinit var appComponent: AppComponent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialiseAppComponent()
        setContentView(R.layout.nav_container)
    }

    private fun initialiseAppComponent(){
        appComponent = DaggerAppComponent.builder()
            .activity(this)
            .build()
    }
}
