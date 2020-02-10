package com.example.chesstimer

import android.os.Bundle
import com.example.chesstimer.basic.BaseActivity
import com.example.chesstimer.dagger2.AppComponent
import com.example.chesstimer.dagger2.DaggerAppComponent
import com.example.chesstimer.dagger2.NavModule

class MainActivity : BaseActivity() {

    companion object {
        lateinit var appComponent: AppComponent
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialiseDagger()
        setContentView(R.layout.navigation_container_fragment)
    }

    fun initialiseDagger(){
        appComponent = DaggerAppComponent.builder()
            .navModule(NavModule(this))
            .build()
    }

}
