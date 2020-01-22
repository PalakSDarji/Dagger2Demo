package com.feedr.blog.dagger2demo.coroutines

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.feedr.blog.dagger2demo.R
import kotlinx.android.synthetic.main.activity_coroutine_demo.*

class CoroutineDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_demo)

        ll.post {
            ll.translationY = - ll.height.toFloat();
            ObjectAnimator.ofFloat(ll,"translationY",-ll.height.toFloat(),0f).apply {
                duration = 1000
                start()
            }
        }



    }

}
