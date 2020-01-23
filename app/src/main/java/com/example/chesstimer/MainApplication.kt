package com.example.chesstimer

import android.app.Application
import com.example.mymvvptest.mvvmm.javakoin.JavaKoin.setAndroidContext
import org.koin.core.KoinApplication
import org.koin.core.context.GlobalContext.start

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }


    private fun startKoin() {
        val koinApp = KoinApplication.create()
            .printLogger()
            .modules(koinModule)
        setAndroidContext(koinApp, this)
        start(koinApp)
    }
}
