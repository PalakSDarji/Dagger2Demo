package com.feedr.blog.dagger2demo

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.layout_service_launcher.*

class ServiceLauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.layout_service_launcher)

        btnStartService.setOnClickListener(View.OnClickListener {
            startService(Intent(this@ServiceLauncherActivity, CustomService::class.java))
        })

        btnStopService.setOnClickListener(View.OnClickListener {
            stopService(Intent(this@ServiceLauncherActivity, CustomService::class.java))
        })
    }
}