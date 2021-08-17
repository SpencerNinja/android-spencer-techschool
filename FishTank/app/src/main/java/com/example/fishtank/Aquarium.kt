package com.example.fishtank

import kotlin.math.PI
//import kotlin.reflect.full.declaredMemberFunctions
//import kotlin.reflect.full.findAnnotation

open class Aquarium (var length: Int = 100, var width: Int = 20, var height: Int = 40) {

    open var volume : Int
        get() = width * height * length / 1000
        set(value) {
            height = (value * 1000) / (width * length)
        }

    open var water = volume * 0.9

    constructor(numberOfFish: Int): this() {
        val water: Int = numberOfFish * 2000 //cm3
        val tank: Double = water + water * 0.1
        height = (tank / (length * width)).toInt()
    }

}

class TowerTank(): Aquarium() {
    override var water = volume * 0.8
    override var volume: Int
        get() = (width * height * length / 1000 * PI).toInt()
        set(value) {
            height = (value * 1000) / (width * length)
        }
}


// Lesson 5 video 17 Annotations
@ImAPlant class Plant {
    fun trim() {}
    fun fertilizer() {}

    @get:OnGet
    val isGrowing: Boolean = true

//    @set:OnSet
//    val needsFood: Boolean = false
}

annotation class ImAPlant

@Target(AnnotationTarget.PROPERTY_GETTER)
annotation class OnGet

@Target(AnnotationTarget.PROPERTY_SETTER)
annotation class OnSet

fun reflections() {
    val classObj = Plant::class

    // print all annotations
    for (annotation in classObj.annotations) {
        println(annotation.annotationClass.simpleName)
    }

//    // find one annotation, or null
//    val annotated = classObj.findAnnotation<ImAPlant>()

//    annotated?.apply {
//        println("Found a plant annotation!")
//    }
}