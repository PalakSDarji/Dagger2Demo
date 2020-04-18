package com.feedr.blog.dagger2demo

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.layout_service_launcher.*

class ServiceLauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_service_launcher)

        btnStartService.setOnClickListener(View.OnClickListener {
            startService(Intent(this@ServiceLauncherActivity, CustomService::class.java))
        })

        btnStopService.setOnClickListener(View.OnClickListener {
            stopService(Intent(this@ServiceLauncherActivity, CustomService::class.java))
        })

        btnGoBind.setOnClickListener{
            startActivity(Intent(this, BinderActivity::class.java))
        }

        //if(Constants.type == "paid"){
        if(BuildConfig.BUILD_TIME == "0"){
            btnLogin.visibility = View.VISIBLE
            btnLogin.setOnClickListener {
                startActivity(Intent(this@ServiceLauncherActivity, LoginActivity::class.java))
            }
        }

        println(Constants.type)
    }
}
