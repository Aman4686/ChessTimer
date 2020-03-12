package com.example.chesstimer.features.creator

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.appcompat.app.AppCompatDialogFragment
import com.example.chesstimer.R

class TimePickerDialog : AppCompatDialogFragment() {

    lateinit var npHours : NumberPicker
    lateinit var npMinutes : NumberPicker
    lateinit var npSeconds : NumberPicker


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val inflater = layoutInflater
        val view = inflater.inflate(R.layout.time_picker_layout , null)
        builder.setView(view)
            .setTitle("Пикай давай")
            .setNegativeButton("cancel" ){
                    dialog :DialogInterface, y: Int -> Unit
            }

        npHours = view.findViewById(R.id.np_hours_timePicker)
        npMinutes = view.findViewById(R.id.np_minutes_timePicker)
        npSeconds = view.findViewById(R.id.np_seconds_timePicker)



        return builder.create()
    }
}