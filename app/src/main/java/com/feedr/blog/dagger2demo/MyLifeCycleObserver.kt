package com.feedr.blog.dagger2demo

import android.app.Activity
import androidx.lifecycle.*

class MyLifeCycleObserver(lifecycleOwner: LifecycleOwner) : LifecycleObserver{

    private lateinit var name : String

    init{
        lifecycleOwner.lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun initVars(){

        name = "Palak"
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun printName(){
        println("name : $name")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroyVars(){
        name = null.toString()
    }
}