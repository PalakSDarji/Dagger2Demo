package com.feedr.blog.dagger2demo.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.feedr.blog.dagger2demo.R
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CoroutineDemoActivity : AppCompatActivity(), CoroutineScope{

    private val job = Job()
    val i by lazy { 5 }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_demo)

        val deferred = async{
            throw Exception("something failed")
        }
        launch(Dispatchers.Main){
            println("launching in Main")
            withContext(Dispatchers.IO){
                println("withContext in IO")
                val userName = callLogin()
                println("withContext in callLogin called : $userName")
            }
        }

    }

    private suspend fun callLogin() : String{

        var loginName = ""
        try{
            Thread.sleep(5000)
        }
        catch (e : Exception){
            e.printStackTrace()
        }
        loginName = "Palak"
        Log.d("DEemo","Login done")

        return loginName
    }
}
