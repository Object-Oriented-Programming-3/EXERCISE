package com.example.objectorientedprogramming3.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TimePicker
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.objectorientedprogramming3.R
import com.example.objectorientedprogramming3.databinding.ActivityAlarmBinding
import org.checkerframework.checker.units.qual.min
import java.text.DateFormat

class AlarmActivity : AppCompatActivity(), TimePickerDialog.OnTimeSetListener {

    private lateinit var binding: ActivityAlarmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_alarm)

        binding.btnTime.setOnClickListener {

            var timePicker = TimePickerFragment()

            timePicker.show(supportFragmentManager, "Time Picker")


        }

        binding.btnCancel.setOnClickListener {

            cancelAlarm()
        }
    }


    override fun onTimeSet(timePicker: TimePicker?, hourOfDay: Int, minute: Int) {

        var c = Calendar.getInstance()

        c.set(Calendar.HOUR_OF_DAY, hourOfDay)
        c.set(Calendar.MINUTE, minute)
        c.set(Calendar.SECOND, 0)

        updateTimeText(c)

        startAlarm(c)
    }

    private fun updateTimeText(c: Calendar){

        var curTime = DateFormat.getTimeInstance(DateFormat.SHORT).format(c.time)

        binding.txtTime.append("알람 시간: ")
        binding.txtTime.append(curTime)
    }
    private fun startAlarm(c: Calendar){

        var alarmManager: AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        var intent = Intent(this, AlertReceiver::class.java )

        var curTime = DateFormat.getTimeInstance(DateFormat.SHORT).format(c.time)
        intent.putExtra("time", curTime)

        var pendingIntent = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_IMMUTABLE)

        if(c.before(Calendar.getInstance())){
            c.add(Calendar.DATE, 1)
        }
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.timeInMillis, pendingIntent)
        Log.d("Hello","${c.timeInMillis - System.currentTimeMillis()}")
    }

    private fun cancelAlarm(){

        var alarmManager: AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        var intent = Intent(this, AlertReceiver::class.java)

        var pendingIntent = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_IMMUTABLE)


        alarmManager.cancel(pendingIntent)
        binding.txtTime.text = "알람취소"

    }
}