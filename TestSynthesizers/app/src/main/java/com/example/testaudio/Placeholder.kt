package com.example.testaudio

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.karlotoy.perfectune.instance.PerfectTune
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import java.util.*

class Placeholder : AppCompatActivity() {

    var listOfFreq = listOf(440.0, 523.2511, 587.3295, 659.2551, 783.9909)
    var listOfPlayers = mutableListOf<PerfectTune>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (freq in listOfFreq) {
            var player = PerfectTune()
            player.tuneFreq = freq
            listOfPlayers.add(player)
        }

    }

    var perfectTuneTest = PerfectTune()
    fun playTune(view: View) {
        if (view.id == R.id.button_sound_play) {
//            for () {
//
//            }
            for (freq in listOfFreq) {
                perfectTuneTest = PerfectTune()
                perfectTuneTest.tuneAmplitude = 10000
                object : CountDownTimer(500, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        perfectTuneTest.tuneFreq = freq
                        perfectTuneTest.playTune()
//                        Toast.makeText(this@MainActivity, "Freq $freq played", Toast.LENGTH_SHORT).show()
                    }
                    override fun onFinish() {
                        perfectTuneTest.stopTune()
                    }
                }.start()
            }
            perfectTuneTest.stopTune()
        }
    }

    fun stopTune(view: View) {
        if (view.id == R.id.button_sound_stop) {
            //stops the tune
            perfectTuneTest.stopTune()
        }
    }

}