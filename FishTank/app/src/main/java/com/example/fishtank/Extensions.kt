package com.example.fishtank

fun String.hasSpaces(): Boolean {
    val found = this.find { it == ' ' }
    return found != null
}

fun extentionsExample() {
    "Does it have spaces?".hasSpaces()  // true
}

open class AquariumPlant(val color: String, private val size: Int)
class GreenLeafyPlant(size: Int): AquariumPlant("Green", size)

fun AquariumPlant.isRed() = color == "Red"

fun AquariumPlant.print() = println("AquariumPlant")
fun GreenLeafyPlant.print() = println("GreenLeafyPlant")

fun staticExample() {
    val plant = GreenLeafyPlant(size = 50)
    plant.print() // GreenLeafyPlant

    val aquariumPlant: AquariumPlant = plant
    aquariumPlant.print() // AquariumPlant
}

//// ALTERNATE
//fun String.hasSpaces() = find { it == ' '} != null
//
//fun extensionsExample() {
//    "Does it have spaces?".hasSpaces() // true
//}
//
//class AquariumPlant(val color: String, private val size: Int)
//
//fun AquariumPlant?.pull() {
//    this?.apply {
//        println("removing $this")
//    }
//}
//fun nullableExample() {
//    val plant: AquariumPlant? = null
//    plant.pull() // ok
//}
