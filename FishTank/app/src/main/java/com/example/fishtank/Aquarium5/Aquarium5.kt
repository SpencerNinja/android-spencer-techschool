package com.example.fishtank.Aquarium5

import android.util.Log

data class Fish (var name: String)

fun fish5 (args: Array<String>) {
    fishExamples()
}

fun fishExamples() {
    val fish = Fish(name = "splashy")

    myWith (fish.name) {
        Log.i("Test6", "${this.capitalize()}")
    }
    Log.i("Test6", "${fish.run {name}}")
    Log.i("Test6", "${fish.apply {}}")

    val fish2 = Fish(name = "splashy").apply { name = "Sharkey" }
    Log.i("Test6", "${fish2.name}")

    println(fish.let{it.name.capitalize()}
            .let{it + "fish"}
            .let{it.length}
            .let{it + 31})
}

fun myWith(name: String, block: String.() -> Unit) {
    name.block()
}



//// INLINES
//data class Fish (var name: String)
//
//fun fish5 (args: Array<String>) {
//    fishExamples()
//}
//
//fun fishExamples() {
//    val fish = Fish(name = "splashy")
//
//    // without inline an object is created every call to myWith
//    myWith(fish.name) {
//        capitalize()
//    }
//
//    // with inline no object is created, and lambda body is inlined here
//    fish.name.capitalize()
//}
//
//inline fun myWith(name: String, block: String.() -> Unit) {
//    name.block()
//}


