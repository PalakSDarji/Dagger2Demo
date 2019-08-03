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

    var myBoolean : (String, Int) -> Boolean = { s, i ->
        s.length > i
    }

    val apply5 : ((Int) -> Int) -> Int = { it(2)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProviders.of(this).get(CoffeeViewModel::class.java)

        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        val coffeeMaker = DaggerCoffeeShop.builder().build()
        coffeeMaker.coffeeMaker().brew()

        MyLifeCycleObserver(this)


        val nameObserver = Observer<String>{
            println("NewName: $it")
        }

        viewModel.name.observe(this,nameObserver)

        println("boolean is : " + setCallback("sdad",3))


        setListener(1) { v -> printMe(v)}

        println("boolean iss : "+apply5{it * it})

        inlineFun { println("calling inline functions") }

        doAsync {
            op1()
            op2()
        }
    }

    private fun op2() {

    }

    private fun op1() {

    }

    private fun setListener(i: Int, s : (v: String) -> Unit){
        //println("setListener : " + s("asd"))
        class My{
            init {
                println("My created")
            }
        }

        val v: My = My()
    }

    fun setCallback(s: String, i: Int) : Boolean{
        return myBoolean(s,i)
    }

    fun printMe(v : String){
        println("v is $v")
    }

    inline fun inlineFun(myFun : ()-> Unit){
        myFun()
        println("Code inside inline fncton")
    }
}

interface OnLoginListener{
    fun onLogin()
}

@Singleton @Component interface CoffeeShop{
    fun coffeeMaker() : CoffeeMaker
}

inline fun doAsync(crossinline f: () -> Unit){
    Thread {f()}.start()
}