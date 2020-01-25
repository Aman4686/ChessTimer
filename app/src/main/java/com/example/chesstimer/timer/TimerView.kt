package com.example.chesstimer.timer

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import com.example.chesstimer.Base.BaseView
import com.example.chesstimer.R
import com.example.chesstimer.databinding.TimerMainBinding

class TimerView(@NonNull inflater: LayoutInflater, @NonNull lifecycleOwner: LifecycleOwner,
                 @Nullable container: ViewGroup , @NonNull val model: TimerViewModel) : BaseView() {

    private val context : Context
    init {
        val mDataBinding : TimerMainBinding = DataBindingUtil.inflate(inflater, R.layout.timer_main, container, false)
        mDataBinding.lifecycleOwner = lifecycleOwner
        mDataBinding.executePendingBindings()
        mDataBinding.viewModel = model

        viewLayout = mDataBinding.root
        context = mDataBinding.root.context
    }


    companion object {
        @BindingAdapter("invertMainBtnColor")
        @JvmStatic
        fun LinearLayout.invertMainBtnColor(bol: Boolean) {
            val context = context
            background = if (bol) {
                context.getDrawable(R.color.colorDark)
            } else {
                context.getDrawable(R.drawable.timer_primary_bottom_btn)
            }
        }
        @BindingAdapter("invertSecondaryBtnColor")
        @JvmStatic
        fun TextView.invertSecondaryBtnColor(bol: Boolean) {
            val context = context
            background = if (bol) {
                context.getDrawable(R.color.colorDark)
            } else {
                context.getDrawable(R.drawable.timer_secondary_btn)
            }
        }
    }
}





