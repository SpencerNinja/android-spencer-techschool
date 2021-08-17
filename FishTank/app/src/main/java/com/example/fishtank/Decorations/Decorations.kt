package com.example.fishtank.Decorations

import android.util.Log

fun main (args: Array<String>) {
    makeDecorations()
}
fun makeDecorations() {

    val d1 = Decorations("granite")
    Log.i("Test2", "$d1")

    val d2 = Decorations("slate")
    Log.i("Test2", "$d2")

    val d3 = Decorations("slate")
    Log.i("Test2", "$d3")

    Log.i("Test2", "${d1.equals(d2)}")
    Log.i("Test2", "${d3.equals(d2)}")

    val d4: Decorations = d3.copy()
    Log.i("Test2", "$d3")
    Log.i("Test2", "$d4")

    val d5 = Decorations2("crystal", "wood", "diver")
    Log.i("Test2", "$d5")

    val (rock, wood, diver) = d5
    Log.i("Test2", "$rock")
    Log.i("Test2", "$wood")
    Log.i("Test2", "$diver")

}
data class Decorations(val rocks: String) {}

data class Decorations2(val rocks: String, val wood: String, val diver: String) {}