package com.example.chesstimer.features.settings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chesstimer.R
import com.example.chesstimer.common.TimerUtils
import com.example.chesstimer.dataBase.dao.SettingEntity
import kotlinx.android.synthetic.main.list_item.view.*

class ListRecyclerAdapter : RecyclerView.Adapter<ListRecyclerAdapter.ViewHolder>() {

    var settingsList = ArrayList<SettingEntity>()
    var checkedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false);
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return settingsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = settingsList[position]
        holder.bind(currentItem)
    }

    fun initSettingList(data: List<SettingEntity>) {
        settingsList.clear()
        settingsList.addAll(data)
        notifyDataSetChanged()
    }

    fun getSelectedItemindex() : Int {
        return checkedPosition
    }


    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val time = itemView.tv_time_settingItem
        val title = itemView.setting_item_title
        val id = itemView.tv_id_settingItem
        val itemFrame = itemView.cl_frame_settingItem

        fun bind(item : SettingEntity){
            val timeFormatted = TimerUtils.getFormattedTime(item.timeDuration)

            time.text = timeFormatted
            id.text = item.id.toString()

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