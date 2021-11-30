package com.example.chesstimer.features.settings

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chesstimer.MainActivity.Companion.appComponent
import com.example.chesstimer.R
import com.example.chesstimer.common.PrefUtils
import com.example.chesstimer.dataBase.dao.SettingEntity
import com.example.chesstimer.databinding.ListLayoutBinding
import dagger.Lazy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListFragment : Fragment(R.layout.list_layout){

    @Inject
    lateinit var factory: Lazy<ListViewModel.Factory>

    lateinit var model : ListViewModel

    private lateinit var binding: ListLayoutBinding

    private val linearLayoutManager = LinearLayoutManager(context)
    private val adapter = ListRecyclerAdapter()

    override fun onAttach(context: Context) {
        appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProvider(this, factory.get())[ListViewModel::class.java]
    }

    private fun initCollectors(){
        viewLifecycleOwner.lifecycleScope.launch {
//            model.settingsListStateFlow.collect {
//                adapter.settingsList = it as ArrayList<SettingEntity>
//                settingRecycler.layoutManager = linearLayoutManager
//                settingRecycler.adapter = adapter
//            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ListLayoutBinding.bind(view)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.executePendingBindings()
        binding.viewModel = model
        initCollectors()
        initOnClickListeners()
    }



    private fun initOnClickListeners(){
        binding.listEdit.setOnClickListener {
            PrefUtils.setGameConfig(adapter.getSelectedItemindex()+1)
            model.navigator.navigateBack()
        }

        binding.settingCreate.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                model.data.insertSetting(SettingEntity(120000))
                adapter.notifyDataSetChanged()
            }
        }
    }
}