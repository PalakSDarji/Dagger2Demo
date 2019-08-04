package com.feedr.blog.dagger2demo.livedatademo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.feedr.blog.dagger2demo.R
import kotlinx.android.synthetic.main.activity_user_detail.*

class UserDetailActivity : AppCompatActivity() {

    private lateinit var userViewModel : UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

        userViewModel.setSelectedUserId(intent.getIntExtra("selectedUserId",0))

        userViewModel.getUserData().observe(this, Observer {
            tvUserDetails.text = it.toString()
        })
    }
}
