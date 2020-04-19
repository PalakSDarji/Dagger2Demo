package com.feedr.blog.dagger2demo

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.util.Base64
import android.util.Log
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class MyApplicationn : Application() {

    companion object{
        val CHANNEL_ID : String = "MyAppMainChannel"
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        FacebookSdk.sdkInitialize(this)
        AppEventsLogger.activateApp(this)
        printHashKey(this)
    }

    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationChannel = NotificationChannel(CHANNEL_ID,"App's Main Channel",NotificationManager.IMPORTANCE_DEFAULT)
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }
    fun printHashKey(pContext: Context) {
        try {
            val info: PackageInfo = pContext.getPackageManager()
                .getPackageInfo(pContext.getPackageName(), PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                val hashKey = String(Base64.encode(md.digest(), 0))
                Log.i("printHashKey()", "printHashKey() Hash Key: $hashKey")
            }
        } catch (e: NoSuchAlgorithmException) {
            Log.e("printHashKey()", "printHashKey()", e)
        } catch (e: Exception) {
            Log.e("printHashKey()", "printHashKey()", e)
        }
    }
}