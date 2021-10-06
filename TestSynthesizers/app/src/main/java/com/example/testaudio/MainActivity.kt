package com.example.testaudio

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.karlotoy.perfectune.instance.PerfectTune
import android.os.CountDownTimer
import android.util.Log
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

    fun createMelody(): List<Note> {
        for (n1 in 1..melodyLength) {
            var randomFrequency = Random.nextDouble(220.0000,880.0000)
            var randomDuration = Random.nextInt(500, 2000)
            var note = Note()
            note.library = PerfectTune()
            note.frequency = randomFrequency
            note.duration = randomDuration
            listOfPlayers.add(note)
        }
        return listOfPlayers
    }

    var perfectTuneTest = PerfectTune()
    fun playMelody(view: View) {
        if (view.id == R.id.button_sound_play) {
            for (n2 in listOfPlayers) {
                Log.i("note", "Step into Loop - note: ${n2.frequency}")
                object : CountDownTimer((n2.duration.toLong()), 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        n2.library.tuneFreq = n2.frequency
                        n2.library.playTune()
                        Log.i("note", "onTick method: Playing note ${n2.frequency} for ${n2.duration} milliseconds")
                    }
                    override fun onFinish() {
                        n2.library.stopTune()
                        Log.i("note", "onFinish method: complete")
                    }
                }.start()
                Log.i("note", "start CountDownTimer")
            }
            Log.i("note", "Finished loop")
        }
    }

//    fun stopTune(view: View) {
//        if (view.id == R.id.button_sound_stop) {
//            //stops the tune
//            perfectTuneTest.stopTune()
//        }
//    }

}
