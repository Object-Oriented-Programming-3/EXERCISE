package com.example.objectorientedprogramming3.alarm
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.DialogFragment
import com.example.objectorientedprogramming3.R
import java.util.*

// 시계 호출 클래스
// UI 구성요소를 객체없이 사용하기 위해 데이터바인딩 필요

class TimePickerFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog{

        var c : Calendar = Calendar.getInstance()

        var hour = c.get(Calendar.HOUR_OF_DAY)
        var minute = c.get(Calendar.MINUTE)

        return TimePickerDialog(activity, activity as TimePickerDialog.OnTimeSetListener,
            hour, minute, DateFormat.is24HourFormat(activity) )
    }
}