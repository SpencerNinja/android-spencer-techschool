package com.example.fishtank

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi

class AquariumCare {
}

@RequiresApi(Build.VERSION_CODES.N)
fun main(args: Array<String>) {
    val symptoms = mutableListOf("white spots", "red spots", "not eating", "bloated", "belly up")
    symptoms.add("white fungus")
    symptoms.remove("white fungus")

    symptoms.contains("red")    // false
    symptoms.contains("red spots")  // true

    Log.i("Test5", "${symptoms.subList(4, symptoms.size)}") // [belly up]
    listOf(1, 5, 3).sum()   // 9
    listOf("a", "b", "cc").sumBy { it.length }  // 4

    val cures = mapOf("white spots" to "Ich", "red sores" to "hole disease")
    val resultCures = cures.get("white spots")
    Log.i("Test5", "$resultCures")
    val resultCures2 = cures["white spots"]
    Log.i("Test5", "$resultCures2")
    val curesDefaultKey = "bloating"
    val curesDefaultValue = "sorry I don't know"
    Log.i("Test5", "${cures.getOrDefault(curesDefaultKey, curesDefaultValue)}")

    cures.getOrElse("bloating") {"No cure for this"}

    val inventory = mutableMapOf("fish net" to 1)
    inventory.put("tank scrubber", 3)
    inventory.remove("fish net")
}