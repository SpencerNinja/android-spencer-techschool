package com.example.fishtank

import android.util.Log

abstract class AquariumFish {
    abstract val color: String
}
class Shark: AquariumFish(), FishAction {
    override val color = "gray"
    override fun eat() {
        Log.i("Test2", "hunt and eat fish")
    }
}
class Plecostomus: AquariumFish(), FishAction {
    override val color = "gold"
    override fun eat() {
        Log.i("Test2", "munch on algae")
    }
}
interface FishAction {
    fun eat()
}
