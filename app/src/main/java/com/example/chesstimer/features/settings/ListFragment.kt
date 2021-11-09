package com.example.chesstimer.features.settings

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.example.chesstimer.MainActivity.Companion.appComponent
import com.example.chesstimer.R
import com.example.chesstimer.base.BaseFragment
import com.example.chesstimer.base.BaseViewModel
import com.example.chesstimer.common.PrefUtils
import com.example.chesstimer.common.states.TimerState
import com.example.chesstimer.dataBase.dao.SettingEntity
import com.example.chesstimer.databinding.ListLayoutBinding
import com.example.chesstimer.features.timer.TimerViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.Lazy
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListFragment : BaseFragment(){


    @BindView(R.id.setting_create)
    lateinit var createNewSettings : FloatingActionButton
    @BindView(R.id.list_edit)
    lateinit var editListSetting : TextView
    @BindView(R.id.setting_recycler)
    lateinit var settingRecycler : RecyclerView

    @Inject
    lateinit var factory: Lazy<ListViewModel.Factory>

    lateinit var model : ListViewModel

    lateinit var unbinder: Unbinder

    private val linearLayoutManager = LinearLayoutManager(context)
    private val adapter = ListRecyclerAdapter()

    override fun onAttach(context: Context) {
        appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProvider(this, factory.get())[ListViewModel::class.java]
        initCollectors()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val settingView = initViewBinding(inflater , this , container!! , model)
        initOnClickListeners()
        return settingView
    }

    private fun initCollectors(){
        lifecycleScope.launch {
            model.settingsListStateFlow.collect {
                adapter.settingsList = it as ArrayList<SettingEntity>
                settingRecycler.layoutManager = linearLayoutManager
                settingRecycler.adapter = adapter
            }
        }
    }





    fun initViewBinding(inflater: LayoutInflater,
                        lifecycleOwner: LifecycleOwner,
                        container: ViewGroup,
                        model: BaseViewModel

    ) : View{
        val mDataBinding : ListLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.list_layout, container, false)
        mDataBinding.lifecycleOwner = lifecycleOwner
        mDataBinding.executePendingBindings()
        mDataBinding.viewModel = model as ListViewModel
        val listView = mDataBinding.root
        unbinder = ButterKnife.bind(this , listView)
        return listView
    }

    private fun initOnClickListeners(){
        editListSetting.setOnClickListener {
            PrefUtils.setGameConfig(adapter.getSelectedItemindex()+1)
            model.navigator.navigateBack()
        }

        createNewSettings.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                model.data.insertSetting(SettingEntity(120000))
                adapter.notifyDataSetChanged()
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unbinder.unbind()
    }



}