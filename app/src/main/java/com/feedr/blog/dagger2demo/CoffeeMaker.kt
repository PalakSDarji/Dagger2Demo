package com.feedr.blog.dagger2demo

import javax.inject.Inject
import javax.inject.Singleton

@Singleton class ElectricHeater @Inject constructor(){
    var heating : Boolean = false
    val isHot get() = heating
    fun on(){
        this.heating = true
    }
    fun off(){
        this.heating = false
    }
}

class Thermosiphon @Inject constructor(private val heater : ElectricHeater){
    fun pump(){
        if (heater.isHot) {
            println("Heater is hot !!!")
        }
    }
}

class CoffeeMaker @Inject constructor(){

    @Inject lateinit var heater : ElectricHeater
    @Inject lateinit var pump : Thermosiphon
    fun brew(){
        println("Brewing coffee")
        heater.on()
        pump.pump()
        println("Coffee ready :)")
        heater.off()

    }

}