package com.feedr.blog.dagger2demo

import android.R
import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat


class CustomService : Service() {

    private val LOG_TAG = "BoundService"

    var myBinder : IBinder = MyBinder()

    override fun onCreate() {
        super.onCreate()
        Log.v(LOG_TAG, "in onCreate")

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Log.v(LOG_TAG, "in onStartCommand")

        val notificationIntent = Intent(this, ServiceLauncherActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0, notificationIntent, 0)

        val notification: Notification = NotificationCompat.Builder(this, MyApplicationn.CHANNEL_ID)
            .setContentTitle("Forground Service")
            .setSmallIcon(R.drawable.ic_media_play)
            .setContentIntent(pendingIntent)
            .build()

        startForeground(1, notification)

        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.v(LOG_TAG, "in onBind")
        return myBinder
    }

    override fun onRebind(intent: Intent?) {
        Log.v(LOG_TAG, "in onRebind")
        super.onRebind(intent)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.v(LOG_TAG, "in onUnbind")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(LOG_TAG, "in onDestroy")
    }

    inner class MyBinder : Binder() {
        public fun getService() : CustomService {
            return this@CustomService
        }
    }
}