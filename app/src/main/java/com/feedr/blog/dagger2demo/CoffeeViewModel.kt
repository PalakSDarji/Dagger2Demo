package com.feedr.blog.dagger2demo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CoffeeViewModel : ViewModel(){

    var number : MutableLiveData<Int> = MutableLiveData()
    var coffeeType : Int = 2
    var name : MutableLiveData<String> = MutableLiveData<String>()

    fun makeIncrementNumber() {
        var value = number.value
        if (value == null) {
            value = 0
        }
        value++
        number.postValue(value)
    }
}