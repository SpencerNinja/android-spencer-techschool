package com.example.testaudio

import com.karlotoy.perfectune.instance.PerfectTune

class Note() {
    var frequency: Double
    var library: PerfectTune
    var duration: Int

    init {
        frequency = 0.0
        library = PerfectTune()
        duration = 1000
    }

}