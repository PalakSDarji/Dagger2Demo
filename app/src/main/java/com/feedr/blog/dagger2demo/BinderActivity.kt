package com.feedr.blog.dagger2demo

import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_binder.*


class BinderActivity : AppCompatActivity() {

    private lateinit var customService: CustomService
    var mServiceBound = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_binder)

        btnBindService.setOnClickListener(View.OnClickListener {
            bindService(Intent(this@BinderActivity, CustomService::class.java)
                , serviceConnection, Context.BIND_AUTO_CREATE)
        })

        btnUnbindService.setOnClickListener(View.OnClickListener {
            if(mServiceBound){
                unbindService(serviceConnection)
                mServiceBound = false
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        if(mServiceBound){
            unbindService(serviceConnection)
            mServiceBound = false
        }
    }

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            mServiceBound = false
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            customService = (service as CustomService.MyBinder).getService()
            mServiceBound = true
        }

    }

}
