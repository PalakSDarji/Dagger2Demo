package com.feedr.blog.dagger2demo

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_binder.*


class BinderActivity : AppCompatActivity() {

    private lateinit var customService: CustomService
    var mServiceBound = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_binder)

        btnBindService.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@BinderActivity, CustomService::class.java)
            bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
            onCustomClickListener.onCustomClick(3)
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

    private val onCustomClickListener = object : OnCustomClickListener {
        override fun onCustomClick() {

        }

        override fun onCustomClick(position: Int) {
            Toast.makeText(this@BinderActivity,"Pos is $position",Toast.LENGTH_LONG).show()
        }
    }
}
