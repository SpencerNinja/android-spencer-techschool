package com.example.fishtank

//class Inheritance {
//}
//
//
//fun main(args: Array<String>) {
//    delegate()
//}
//fun delegate() {
//    val pleco = Plecostomus()
//    println("Fish has color ${pleco.color}")
//}
//interface FishAction {
//    fun eat()
//}
//interface FishColor {
//    val color: String
//}
//class Plecostomus(fishColor: FishColor = GoldColor):
//        FishAction by PrintingFishAction(food = "a lot of algae"),
//        FishColor by fishColor
//object GoldColor: FishColor {
//    override val color = "gold"
//}
//object RedColor: FishColor {
//    override val color = "red"
//}
//class PrintingFishAction(val food: String): FishAction {
//    override fun eat() {
//        println(food)
//    }
//}


//// Interface
//interface AquariumAction {
//    fun eat()
//    fun jump()
//    fun clean()
//    fun catchFish()
//    fun swim() {
//        println("swim")
//    }
//}
//// Abstract Class
//interface FishAction {
//    fun eat()
//}
//abstract class AquariumFish: FishAction {
//    abstract val color: String
//    override fun eat() = println("yum")
//}
//// Abstract Classes
//    // CAN have constructors
//    // use an abstract class anytime you can't complete a class
//// Interfaces
//    // CANNOT have constructors
//    // use an interface if you have a lot of methods and one or two default implementations
//
//// both have have implementations of methods