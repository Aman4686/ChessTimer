package com.example.chesstimer.features.settings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chesstimer.R
import com.example.chesstimer.common.TimerUtils
import com.example.chesstimer.data.SettingData
import com.example.chesstimer.dataBase.SettingEntity
import kotlinx.android.synthetic.main.setting_item.view.*

class SettingRecyclerAdapter : RecyclerView.Adapter<SettingRecyclerAdapter.ViewHolder>() {

    var settingsList = ArrayList<SettingData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.setting_item, parent, false);
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return settingsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = settingsList[position]
        val time = TimerUtils.getTimeLeftFormatted(currentItem.time)

        holder.time.text = time
        holder.id.text = currentItem.id.toString()
        holder.title.text = currentItem.title
    }

    fun initSettingList(data: List<SettingData>) {
        settingsList.clear()
        settingsList.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val time = itemView.setting_item_time
        val title = itemView.setting_item_title
        val id = itemView.setting_item_id
    }

}