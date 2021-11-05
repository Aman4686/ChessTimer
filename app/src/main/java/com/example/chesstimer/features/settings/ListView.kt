package com.example.chesstimer.features.settings

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.chesstimer.R
import com.example.chesstimer.base.BaseView
import com.example.chesstimer.base.BaseViewModel
import com.example.chesstimer.common.PrefUtils
import com.example.chesstimer.dataBase.dao.SettingEntity
import com.example.chesstimer.databinding.ListLayoutBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers

class ListView(inflater: LayoutInflater,  lifecycleOwner: LifecycleOwner,
                container: ViewGroup,  model: ListViewModel) : BaseView() {


    @BindView(R.id.setting_create)
    lateinit var createNewSettings : FloatingActionButton
    @BindView(R.id.list_edit)
    lateinit var editListSetting : TextView
    @BindView(R.id.setting_recycler)
    lateinit var settingRecycler : RecyclerView


    override fun getLayoutId(): Int {
       return R.layout.list_layout
    }


    override fun initViewBinding(inflater: LayoutInflater, lifecycleOwner: LifecycleOwner,
                                container: ViewGroup, model: BaseViewModel
    ){
        val mDataBinding : ListLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.list_layout, container, false)
        mDataBinding.lifecycleOwner = lifecycleOwner
        mDataBinding.executePendingBindings()
        mDataBinding.viewModel = model as ListViewModel
        viewLayout = mDataBinding.root
        unbinder = ButterKnife.bind(this , viewLayout)
        context = mDataBinding.root.context
    }

    init {
        initViewBinding(inflater , lifecycleOwner , container , model)
        model.initList()

        val linearLayoutManager = LinearLayoutManager(context)
        val adapter = ListRecyclerAdapter()

        model.settingListModel.observe(lifecycleOwner , Observer {

            adapter.settingsList = it as ArrayList<SettingEntity>
            settingRecycler.layoutManager = linearLayoutManager
            settingRecycler.adapter = adapter
        })
        createNewSettings.setOnClickListener {
            model.data.insertSetting(SettingEntity(120000))
            adapter.notifyDataSetChanged()
        }
        editListSetting.setOnClickListener {
            PrefUtils.setGameConfig(adapter.getSelectedItemindex()+1)
            model.navigator.navigateBack()
        }
    }


}