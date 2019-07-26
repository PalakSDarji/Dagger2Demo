package com.feedr.blog.dagger2demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.feedr.blog.dagger2demo.databinding.ActivityMainBinding
import dagger.Component
import javax.inject.Singleton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProviders.of(this).get(CoffeeViewModel::class.java)

        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        val coffeeMaker = DaggerCoffeeShop.builder().build()
        coffeeMaker.coffeeMaker().brew()

        MyLifeCycleObserver(this)

        setCallback(object : OnLoginListener {
            override fun onLogin() {

            }
        })


    }

    fun setCallback(d: OnLoginListener){
        d.onLogin()
    }
}

interface OnLoginListener{
    fun onLogin()
}

@Singleton @Component interface CoffeeShop{
    fun coffeeMaker() : CoffeeMaker
}