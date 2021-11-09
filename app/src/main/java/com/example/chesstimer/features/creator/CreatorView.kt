package com.example.chesstimer.features.creator

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import butterknife.BindView
import butterknife.ButterKnife
import com.example.chesstimer.R
import com.example.chesstimer.base.BaseView
import com.example.chesstimer.base.BaseViewModel
import com.example.chesstimer.common.Duration
import com.example.chesstimer.common.TimerUtils
import com.example.chesstimer.common.TimerUtils.hoursToMillis
import com.example.chesstimer.common.TimerUtils.minutesToMillis
import com.example.chesstimer.common.TimerUtils.secondsToMillis
import com.example.chesstimer.common.states.TimerState
import com.example.chesstimer.dataBase.dao.SettingEntity
import com.example.chesstimer.databinding.CreatorLayoutBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CreatorView(
    inflater: LayoutInflater,
    lifecycleOwner: LifecycleOwner,
    container: ViewGroup,
    private val model: CreatorViewModel,
    private val fragment: FragmentManager?
) : BaseView() {

    @BindView(R.id.btn_save_creator)
    lateinit var save : TextView

    @BindView(R.id.ed_time_creator)
    lateinit var itemTime : TextView

    lateinit var settingEntity : SettingEntity

    override fun getLayoutId(): Int {
        return R.layout.creator_layout
    }
     fun initViewBinding(inflater: LayoutInflater, lifecycleOwner: LifecycleOwner,
                                 container: ViewGroup, model: BaseViewModel
    ){
        val mDataBinding : CreatorLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.creator_layout, container, false)
        mDataBinding.lifecycleOwner = lifecycleOwner
        mDataBinding.executePendingBindings()
        mDataBinding.viewModel = model as CreatorViewModel
        viewLayout = mDataBinding.root
        unbinder = ButterKnife.bind(this , mDataBinding.root)
        context = mDataBinding.root.context
    }

    init {
        initViewBinding(inflater , lifecycleOwner , container , model)
        initOnClick()


        lifecycleOwner.lifecycleScope.launch {
            model.settingLiveData
                .collect{ settingEntity = it }
        }

    }

    private fun initOnClick() {
        itemTime.setOnClickListener{
            if(fragment != null) {
                val timePickerDialog = initTimePickerDialog(Duration(settingEntity.timeDuration))
                timePickerDialog.show(fragment, "SimpleDialog")
            }
        }
    }


    private fun initTimePickerDialog(duration : Duration) : TimePickerDialog{

       return TimePickerDialog(duration){hour , min , sec->
           val result = hoursToMillis(hour) + minutesToMillis(min) + secondsToMillis(sec)

           settingEntity.timeDuration = result
           model.settingLiveData.value = settingEntity
           itemTime.text = TimerUtils.getFormattedTime(result)
        }

    }

}