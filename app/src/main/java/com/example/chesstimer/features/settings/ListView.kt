package com.example.chesstimer.features.settings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.chesstimer.R
import com.example.chesstimer.basic.BaseView
import com.example.chesstimer.basic.BaseViewModel
import com.example.chesstimer.databinding.ListLayoutBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListView(inflater: LayoutInflater,  lifecycleOwner: LifecycleOwner,
                container: ViewGroup,  model: ListViewModel) : BaseView() {

    @BindView(R.id.setting_create)
    lateinit var createNewSettings : FloatingActionButton
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

        settingRecycler.layoutManager = GridLayoutManager(context , 2)
        settingRecycler.adapter = model.adapter

    }


}