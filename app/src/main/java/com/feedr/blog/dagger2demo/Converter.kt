package com.feedr.blog.dagger2demo

import android.view.View
import android.widget.EditText
import androidx.databinding.InverseMethod

object Converter {

    @JvmStatic
    @InverseMethod("stringToDate")
    fun dateToString(value : Int) : String{
        if(value == 1){
            return "Coffee1"
        }
        else{
            return "Coffee2"
        }
    }

    @JvmStatic
    fun stringToDate(value : String) : Int{
        if(value.equals("Coffee1")){
            return 1
        }
        else{
            return 2
        }
    }
}