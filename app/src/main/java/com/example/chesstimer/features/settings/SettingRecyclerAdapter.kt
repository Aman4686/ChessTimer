package com.example.chesstimer.features.settings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chesstimer.R
import com.example.chesstimer.common.TimerUtils
import com.example.chesstimer.data.SettingData
import kotlinx.android.synthetic.main.setting_item.view.*

class SettingRecyclerAdapter : RecyclerView.Adapter<SettingRecyclerAdapter.ViewHolder>() {

    var settingsList = ArrayList<SettingData>()
    var checkedPosition = 0

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

        if (checkedPosition == -1) {
            holder.id.visibility = View.GONE;
        } else {
            if (checkedPosition == holder.adapterPosition) {
                holder.id.visibility = View.VISIBLE;
            } else {
                holder.id.visibility = View.GONE;
            }
        }

        holder.item.setOnClickListener {
            holder.id.visibility = View.VISIBLE
            if (checkedPosition != holder.adapterPosition) {
                notifyItemChanged(checkedPosition)
                checkedPosition = holder.adapterPosition
            }
        }

    }

    fun initSettingList(data: List<SettingData>) {
        settingsList.clear()
        settingsList.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val time = itemView.tv_time_settingItem
        val title = itemView.setting_item_title
        val id = itemView.tv_id_settingItem
        val item = itemView.cl_frame_settingItem
    }




}