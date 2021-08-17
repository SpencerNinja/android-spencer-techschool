package com.example.fishtank

import android.util.Log

class main {
    fun buildAquarium() {
        val myAquarium = Aquarium()
        Log.i("Test2", "'Length: ${myAquarium.length} ' + 'Width: ${myAquarium.width} ' + 'Height: ${myAquarium.height}'")
        myAquarium.height = 80
        Log.i("Test2", "Height: ${myAquarium.height} cm")
        Log.i("Test2", "Volume: ${myAquarium.volume} liters")
        val smallAquarium = Aquarium(length = 20, width = 15, height = 30)
        Log.i("Test2","Small Aquarium: ${smallAquarium.volume} liters")
        val myAquarium2 = Aquarium(numberOfFish = 9)
        Log.i("Test2","'Small Aquarium 2: ${myAquarium2.volume} liters with ' + 'length ${myAquarium2.length} ' + 'width: ${myAquarium2.width} ' + 'height: ${myAquarium2.height} '")
    }
    fun feedFish(fish: FishAction) {
        fish.eat()
    }
    fun makeFish() {
        val shark = Shark()
        val pleco = Plecostomus()
        Log.i("Test2", "Shark: ${shark.color} \n Plecostomus: ${pleco.color}")
        shark.eat()
        pleco.eat()
    }

    fun main(args: Array<String>) {
        buildAquarium()
        makeFish()
    }
}