package com.example.fishtank

import android.util.Log

class Fish(val friendly: Boolean = true, volumeNeeded: Int) {
    val size: Int
    init {
        Log.i("Test2","first init block")
    }
    constructor() : this( true, 9) {
        Log.i("Test2","running secondary constructor")
    }
    init {
        if (friendly) {
            size = volumeNeeded
        } else {
            size = volumeNeeded * 2
        }
    }
    init {
        Log.i("Test2","constructed fish of size $volumeNeeded final size ${this.size}")
    }
    init {
        Log.i("Test2","last init block")
    }
}

fun makeDefaultFish() = Fish(true, 50)
fun fishExample() {
    val fish = Fish(true, 20)
    Log.i("Test2","Is the fish friendly? ${fish.friendly}. It needs volume ${fish.size}")
}