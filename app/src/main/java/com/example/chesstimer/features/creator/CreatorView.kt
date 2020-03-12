package com.example.chesstimer.features.creator

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import com.example.chesstimer.R
import com.example.chesstimer.basic.BaseView
import com.example.chesstimer.common.TimerUtils
import com.example.chesstimer.databinding.CreatorLayoutBinding

class CreatorView(@NonNull inflater: LayoutInflater, @NonNull lifecycleOwner: LifecycleOwner,
                  @Nullable container: ViewGroup, @NonNull val model: CreatorViewModel) : BaseView() {

    lateinit var save : TextView
    lateinit var itemTitle : EditText
    lateinit var itemTime : EditText

    init {
        val mDataBinding : CreatorLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.creator_layout, container, false)
        mDataBinding.lifecycleOwner = lifecycleOwner
        mDataBinding.executePendingBindings()
        mDataBinding.viewModel = model
        viewLayout = mDataBinding.root
        initIds()

        

        save.setOnClickListener{
            val title = itemTitle.text.toString()
            val minutes = itemTime.text.toString().toInt()
            model.onSaveCliked(title , TimerUtils.secondsToMillis(minutes))
        }
    }

    private fun initIds() {
        save = viewLayout.findViewById(R.id.btn_save_creator)
        itemTitle = viewLayout.findViewById(R.id.ed_title_creator)
        itemTime = viewLayout.findViewById(R.id.ed_time_creator)
    }


}