package com.example.chesstimer.features.settings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chesstimer.R

class SettingRecyclerAdapter : RecyclerView.Adapter<SettingRecyclerAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.setting_item, parent, false);
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 0;
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    fun setSettingsList() {

        notifyDataSetChanged()
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        init {

        }

    }

}