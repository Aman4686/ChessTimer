package com.example.chesstimer.Base

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import com.example.mymvvptest.mvvmm.javakoin.JavaKoin
import org.koin.core.scope.Scope
import org.koin.java.KoinJavaComponent

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity(){
    private var scope: Scope? = null

    fun createScope() {
        scope = JavaKoin.createScopeWithContext(this)
    }

    private fun disposeScope() {
        if (scope != null) {
            scope!!.close()
            scope = null
        }
    }

    override fun onDestroy() {
        disposeScope()
        super.onDestroy()
    }

    protected fun <T : Any> instanceOf(componentClass: Class<T>): T {
        val result: T
        if (scope != null)
            result = scope!!.get(componentClass)
        else
            result = KoinJavaComponent.get(componentClass)
        return result

}
}