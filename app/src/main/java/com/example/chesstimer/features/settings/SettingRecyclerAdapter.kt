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
        holder.bind(currentItem)
    }

    fun initSettingList(data: List<SettingData>) {
        settingsList.clear()
        settingsList.addAll(data)
        notifyDataSetChanged()
    }

    fun getSelectedItemId() : Int = settingsList[checkedPosition].id

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val time = itemView.tv_time_settingItem
        val title = itemView.setting_item_title
        val id = itemView.tv_id_settingItem
        val itemFrame = itemView.cl_frame_settingItem

        fun bind(item : SettingData){
            val timeFormatted = TimerUtils.getTimeLeftFormatted(item.time)

            time.text = timeFormatted
            id.text = item.id.toString()
            title.text = item.title

                if (checkedPosition == adapterPosition) {
                    id.visibility = View.VISIBLE
                } else {
                    id.visibility = View.GONE
                }


            itemFrame.setOnClickListener {
               id.visibility = View.VISIBLE
                if (checkedPosition != adapterPosition) {
                    notifyItemChanged(checkedPosition)
                    checkedPosition = adapterPosition
                }
            }
        }
    }






}