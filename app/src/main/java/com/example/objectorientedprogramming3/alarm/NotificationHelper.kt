package com.example.objectorientedprogramming3.alarm

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import com.example.objectorientedprogramming3.R

class NotificationHelper(base: Context?) : ContextWrapper(base) {
    private val channelID = "channelID"
    private val channelNm = "channelNm"
    // 안드로이드 버전이 오레오거나 이상이면 채널을 생성
    init {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            createChannel()
        }
    }
    // 채널생성 함수
    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel(){
        var channel = NotificationChannel(channelID, channelNm, NotificationManager.IMPORTANCE_DEFAULT)

        channel.enableLights(true)
        channel.enableVibration(true)
        channel.lightColor = Color.GREEN
        channel.lockscreenVisibility = Notification.VISIBILITY_PRIVATE

        getManager().createNotificationChannel(channel)
    }
    // NotificationManager 생성
    fun getManager(): NotificationManager{

        return getSystemService(NOTIFICATION_SERVICE) as NotificationManager
    }
    // Notification 설정
    fun getChannelNotification(time: String?) : NotificationCompat.Builder{

        return NotificationCompat.Builder(applicationContext, channelID)
            .setContentTitle(time)
            .setContentText("운동할시간입니다!!!!")
            .setSmallIcon(R.drawable.ic_launcher_background)
    }

}