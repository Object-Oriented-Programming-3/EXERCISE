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
import com.example.objectorientedprogramming3.R
import com.example.objectorientedprogramming3.databinding.ActivityAlarmBinding
import java.text.DateFormat

class AlarmActivity : AppCompatActivity(), TimePickerDialog.OnTimeSetListener {

    private lateinit var binding: ActivityAlarmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)
        setTitle("알람 페이지")

        binding = DataBindingUtil.setContentView(this, R.layout.activity_alarm)

        // 알람성정
        binding.btnTime.setOnClickListener {

            var timePicker = TimePickerFragment()
            // 시계를 호출
            timePicker.show(supportFragmentManager, "Time Picker")


        }
        // 알람취소
        binding.btnCancel.setOnClickListener {

            cancelAlarm()
        }
    }

    // TimePickerDialog 에서 시간선택이 끝나면 호출될 함수
    override fun onTimeSet(timePicker: TimePicker?, hourOfDay: Int, minute: Int) {

        var c = Calendar.getInstance()
        // 시간 설정
        c.set(Calendar.HOUR_OF_DAY, hourOfDay)
        c.set(Calendar.MINUTE, minute)
        c.set(Calendar.SECOND, 0)

        // 시간을 화면에 지정
        updateTimeText(c)

        //알람설정
        startAlarm(c)
    }

    // 선택한 시간 표시함수
    private fun updateTimeText(c: Calendar){

        var curTime = DateFormat.getTimeInstance(DateFormat.SHORT).format(c.time)

        binding.txtTime.append("알람 시간: ")
        binding.txtTime.append(curTime)
    }

    // 알람 설정 함수
    private fun startAlarm(c: Calendar){

        var alarmManager: AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        var intent = Intent(this, AlertReceiver::class.java )

        var curTime = DateFormat.getTimeInstance(DateFormat.SHORT).format(c.time)
        intent.putExtra("time", curTime)

        var pendingIntent = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_IMMUTABLE)

        // 설정시간이 현재시간 이전이면 +1일을 해줌
        if(c.before(Calendar.getInstance())){
            c.add(Calendar.DATE, 1)
        }
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.timeInMillis, pendingIntent)
        Log.d("Hello","${c.timeInMillis - System.currentTimeMillis()}")
    }

    // 알람 취소함수
    private fun cancelAlarm(){
        // 알람 매니저 선언
        var alarmManager: AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        var intent = Intent(this, AlertReceiver::class.java)

        var pendingIntent = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_IMMUTABLE)


        alarmManager.cancel(pendingIntent)
        binding.txtTime.text = "알람취소"

    }
}