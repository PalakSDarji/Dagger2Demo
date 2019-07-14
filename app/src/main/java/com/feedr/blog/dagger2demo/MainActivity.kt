package com.feedr.blog.dagger2demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.Component
import javax.inject.Singleton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val coffeeMaker = DaggerCoffeeShop.builder().build()
        coffeeMaker.coffeeMaker().brew()
    }
}


@Singleton @Component interface CoffeeShop{
    fun coffeeMaker() : CoffeeMaker
}