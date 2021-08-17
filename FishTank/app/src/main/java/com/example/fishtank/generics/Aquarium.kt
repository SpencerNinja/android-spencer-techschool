package com.example.fishtank.generics

open class WaterSupply(var needsProcessed: Boolean)

class TapWater: WaterSupply(true) {
    fun addChemicalCleaners() {
        needsProcessed = false
    }
}

class FishStoreWater: WaterSupply(false)

class LakeWater: WaterSupply(true) {
    fun filter() {
        needsProcessed = false
    }
}

class Aquarium<out T: WaterSupply>(val waterSupply: T) {
    fun addWater(cleaner: Cleaner<T>) {
        if(waterSupply.needsProcessed) {
            cleaner.clean(waterSupply)
        }
        println("adding water from $waterSupply")
    }

}
inline fun <reified R: WaterSupply> Aquarium<*>.hasWaterSupplyOfType() = waterSupply is R

fun genericExample() {
    val aquarium = Aquarium<TapWater>(TapWater())
    aquarium.waterSupply.addChemicalCleaners()

    val aquarium4 = Aquarium(LakeWater())
    aquarium4.waterSupply.filter()
//    aquarium4.addWater(cleaner =)
}

interface Cleaner<in T: WaterSupply> {
    fun clean(waterSupply: T)
}

class TapWaterCleaner: Cleaner<TapWater> {
    override fun clean(waterSupply: TapWater) {
        waterSupply.addChemicalCleaners()
    }
}

fun addItemTo(aquarium: Aquarium<WaterSupply>) = println("item added")

fun genericExample2() {
    val cleaner = TapWaterCleaner()
    val aquarium = Aquarium(TapWater())
//    addItemTo((aquarium))
    aquarium.addWater(cleaner)
}

fun <T: WaterSupply> isWaterClean(aquarium: Aquarium<WaterSupply>) {
    println("aquarium water is clean: ${aquarium.waterSupply.needsProcessed}")
}

inline fun <reified T: WaterSupply> WaterSupply.isOfType() = this is T

fun genericExample3() {
    val aquarium = Aquarium(TapWater())
    aquarium.hasWaterSupplyOfType<TapWater>() // true
    aquarium.waterSupply.isOfType<LakeWater>() // false
}