package com.example.chesstimer.features.creator

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import com.example.chesstimer.R
import com.example.chesstimer.basic.BaseView
import com.example.chesstimer.common.Duration
import com.example.chesstimer.common.TimerUtils
import com.example.chesstimer.common.TimerUtils.hoursToMillis
import com.example.chesstimer.common.TimerUtils.minutesToMillis
import com.example.chesstimer.common.TimerUtils.secondsToMillis
import com.example.chesstimer.dataBase.TemporaryEntity
import com.example.chesstimer.databinding.CreatorLayoutBinding

class CreatorView(@NonNull inflater: LayoutInflater, @NonNull lifecycleOwner: LifecycleOwner,
                  @Nullable container: ViewGroup, @NonNull val model: CreatorViewModel , val fragment: FragmentManager?) : BaseView() {

    lateinit var save : TextView
    lateinit var itemTitle : EditText
    lateinit var itemTime : TextView
    lateinit var temporaryEntity : TemporaryEntity
    lateinit var timePickerDialog : TimePickerDialog

    init {
        val mDataBinding : CreatorLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.creator_layout, container, false)
        mDataBinding.lifecycleOwner = lifecycleOwner
        mDataBinding.executePendingBindings()
        mDataBinding.viewModel = model
        viewLayout = mDataBinding.root
        initIds()
        initOnClick()

        model.temporaryLiveData.observe(lifecycleOwner, Observer {
            temporaryEntity = it
        })

    }

    private fun initOnClick() {
        itemTime.setOnClickListener{
            if(fragment != null) {
                timePickerDialog = initTimePickerDialog(Duration(temporaryEntity.timeDuration))
                timePickerDialog.show(fragment, "SimpleDialog")
            }
        }

        save.setOnClickListener {
            val title = itemTitle.text.toString()
            val time = temporaryEntity.timeDuration
            model.onSaveCliked(title , time)
        }
    }

    private fun initIds() {
        save = viewLayout.findViewById(R.id.btn_save_creator)
        itemTitle = viewLayout.findViewById(R.id.ed_title_creator)
        itemTime = viewLayout.findViewById(R.id.ed_time_creator)
    }

    private fun initTimePickerDialog(duration : Duration) : TimePickerDialog{

       return TimePickerDialog(duration){hour , min , sec->
           val result = hoursToMillis(hour) + minutesToMillis(min) + secondsToMillis(sec)

           temporaryEntity.timeDuration = result
           model.temporaryLiveData.value = temporaryEntity

           itemTime.text = TimerUtils.getFormattedTime(result)
        }

    }

}