package com.example.chesstimer

import android.os.Bundle
import com.example.chesstimer.basic.BaseActivity
import com.example.chesstimer.module.NavModule


class MainActivity : BaseActivity() {

    companion object{
        lateinit var appComponent: AppComponent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialiseAppComponent()
        setContentView(R.layout.navigation_container_fragment)
    }

    private fun initialiseAppComponent(){
        appComponent = DaggerAppComponent.builder()
            .navModule(NavModule(this))
            .build()
    }


    override fun onDestroy() {
        releaseMainComponent()
        super.onDestroy()
    }

    private fun releaseMainComponent() {

    }

}
