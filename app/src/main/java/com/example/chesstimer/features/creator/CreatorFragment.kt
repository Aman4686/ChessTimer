package com.example.chesstimer.features.creator

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.chesstimer.MainActivity.Companion.appComponent
import com.example.chesstimer.R
import com.example.chesstimer.common.Duration
import com.example.chesstimer.common.TimerUtils
import com.example.chesstimer.dataBase.dao.SettingEntity
import com.example.chesstimer.databinding.CreatorLayoutBinding
import com.example.chesstimer.databinding.TimerLayoutBinding
import dagger.Lazy
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreatorFragment : Fragment(R.layout.creator_layout) {

    lateinit var model : CreatorViewModel

    private lateinit var binding: CreatorLayoutBinding
    lateinit var settingEntity : SettingEntity

    @Inject
    lateinit var factory: Lazy<CreatorViewModel.Factory>

    override fun onAttach(context: Context) {
        appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProvider(this, factory.get())[CreatorViewModel::class.java]

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = CreatorLayoutBinding.bind(view)
        binding.viewModel = model
        initCollectors()
        initOnClick()
    }

    private fun initCollectors(){

        viewLifecycleOwner.lifecycleScope.launch {
            model.settingLiveData
                .collect{
                    settingEntity = it
                }
        }
    }

    private fun initTimePickerDialog(duration : Duration) : TimePickerDialog{

        return TimePickerDialog(duration){hour , min , sec->
            val result = TimerUtils.hoursToMillis(hour) + TimerUtils.minutesToMillis(min) + TimerUtils.secondsToMillis(sec)
            settingEntity.timeDuration = result
            model.settingLiveData.value = settingEntity
            binding.edTimeCreator.text = TimerUtils.getFormattedTime(result)
        }

    }

    private fun initOnClick() {
        binding.edTimeCreator.setOnClickListener{
            val timePickerDialog = initTimePickerDialog(Duration(settingEntity.timeDuration))
            timePickerDialog.show(parentFragmentManager, "SimpleDialog")
        }
    }

}