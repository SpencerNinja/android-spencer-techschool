package com.example.testaudio

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.karlotoy.perfectune.instance.PerfectTune
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import java.util.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    // How many notes
    var melodyLength = 7
    // 1. Create an empty list to later store notes
    var listOfPlayers = mutableListOf<Note>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createMelody()
    }

    fun createMelody() {
        for (n in 0..melodyLength) {
            var randomFrequency = Random.nextDouble(220.0000,880.0000)
            var randomDuration = Random.nextInt(500, 2000)
            var note = Note()
            note.library = PerfectTune()
            note.frequency = randomFrequency
            note.duration = randomDuration
            listOfPlayers.add(note)
        }
    }

    var perfectTuneTest = PerfectTune()
    fun playTune(view: View) {
        if (view.id == R.id.button_sound_play) {
            for (note in listOfPlayers) {
                object : CountDownTimer(500, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        note.library.tuneFreq = note.frequency
                        note.library.playTune()
                        Toast.makeText(this@MainActivity, "Freq ${note.frequency} played", Toast.LENGTH_SHORT).show()
                    }
                    override fun onFinish() {
                        note.library.stopTune()
                    }
                }.start()
            }
        }
    }

//    fun stopTune(view: View) {
//        if (view.id == R.id.button_sound_stop) {
//            //stops the tune
//            perfectTuneTest.stopTune()
//        }
//    }

}
