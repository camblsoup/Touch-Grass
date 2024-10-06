package com.example.mytestapplication.notifications

import android.os.Build
import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.mytestapplication.R

class NotificationHandler(private val context : Context) {
    var builder = NotificationCompat.Builder(context, context.getString(R.string.channel_id))
        .setSmallIcon(R.drawable.notification_icon)
        .setContentTitle("New activity!")
        .setContentText("Come complete ")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)


}