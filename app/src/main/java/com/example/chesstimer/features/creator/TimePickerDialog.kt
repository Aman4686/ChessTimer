package com.example.chesstimer.features.creator

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.chesstimer.R
import org.w3c.dom.Text
import java.lang.String
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*


class TimePickerDialog : DialogFragment() {

    lateinit var npHours : NumberPicker
    lateinit var npMinutes : NumberPicker
    lateinit var npSeconds : NumberPicker
    lateinit var btnOk : TextView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.time_picker_layout ,container, false)
        initIds(view)
        npHours.maxValue = 24
        npHours.minValue = 0

        npMinutes.maxValue = 59
        npMinutes.minValue = 0

        npSeconds.maxValue = 59
        npSeconds.minValue = 0

        btnOk.setOnClickListener {
            dialog?.dismiss()
        }

        val formatter = NumberPicker.Formatter {
           String.format( Locale.US,"%02d", it )
        }
        npHours.setFormatter(formatter)
        npMinutes.setFormatter(formatter)
        npSeconds.setFormatter(formatter)


        return view
    }

    fun initIds(view : View){
        npHours = view.findViewById(R.id.np_hours_timePicker)
        npMinutes = view.findViewById(R.id.np_minutes_timePicker)
        npSeconds = view.findViewById(R.id.np_seconds_timePicker)
        btnOk = view.findViewById(R.id.tv_ok_picker)
    }

    interface DialoggListner{
        fun getDuration(hours : Int , minutes : Int , seconds : Int) : Long
    }


}
