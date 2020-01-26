package com.example.mymvvptest.mvvmm.javakoin

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.definition.DefinitionFactory
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.TypeQualifier
import org.koin.core.scope.Scope
import org.koin.core.scope.ScopeID
import org.koin.ext.getFullName
import org.koin.java.KoinJavaComponent.getKoin

object JavaKoin {

    @JvmStatic
    private fun createScope(context: Context): Scope{
        return getKoin().createScope(scopeId(context) , nameOf(context))
    }

    @JvmStatic
    fun createScopeWithContext(context: Context): Scope{
        var result = createScope(context)
        setScopeContext(result , context)
        return result
    }

    private fun setScopeContext(scope: Scope, context: Context) {
        var registry = scope.beanRegistry
        registry.saveDefinition(DefinitionFactory.createSingle { androidContext() })
        if(context is AppCompatActivity){
            registry.saveDefinition(DefinitionFactory.createSingle<AppCompatActivity> { context })
        }
    }

    @JvmStatic
    fun setAndroidContext(koinApp: KoinApplication, androidContext: Context): KoinApplication {
        val registry = koinApp.koin.rootScope.beanRegistry
        registry.saveDefinition(DefinitionFactory.createSingle { androidContext })
        if (androidContext is Application)
            registry.saveDefinition(DefinitionFactory.createSingle<Application> { androidContext })
        return koinApp
    }

    @JvmStatic
    fun scopeOf(context: Context): Scope {
        return getKoin().getScope(scopeId(context))
    }

    @JvmStatic
    private fun nameOf(instances: Any): Qualifier {
        return TypeQualifier(instances::class)
    }

    @JvmStatic
    private fun scopeId(instances: Any): ScopeID {
        return instances::class.getFullName() + "@" +System.identityHashCode(instances)
    }

}