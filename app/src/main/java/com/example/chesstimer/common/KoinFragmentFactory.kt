package com.example.chesstimer.common

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.preference.PreferenceFragmentCompat
import com.example.chesstimer.basic.BaseFragment
import com.example.mymvvptest.mvvmm.javakoin.JavaKoin

class KoinFragmentFactory(private val activity: AppCompatActivity) : FragmentFactory(){
    private val baseFragmentClass = BaseFragment::class.java
    private val preferenceFragmentCompatClass = PreferenceFragmentCompat::class.java


    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        val clazz = loadFragmentClass(classLoader, className)
        return if (baseFragmentClass.isAssignableFrom(clazz) || preferenceFragmentCompatClass.isAssignableFrom(
                clazz
            )
        )
            JavaKoin.scopeOf(activity).get(clazz)
        else
            super.instantiate(classLoader, className)
    }
}