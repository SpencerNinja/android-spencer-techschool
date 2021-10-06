package com.example.testaudio

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.karlotoy.perfectune.instance.PerfectTune
import android.view.View
import java.util.*
import kotlin.random.Random



class MainActivity : AppCompatActivity() {

    // How many notes
    var melodyLength = 7
    // 1. Create an empty list to later store notes
    var notes = mutableListOf<Note>()

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
            note.toneObject = PerfectTune()
            note.frequency = randomFrequency
            note.duration = randomDuration
            notes.add(note)
        }
        return notes
    }

    /**
     * PLAY button
     */
//    fun playMelody(view: View) {
//        if (view.id == R.id.button_sound_play) {
//            for (n2 in listOfPlayers) {
//                Log.i("note", "Step into Loop - note: ${n2.frequency}")
//                n2.assignFrequency()
//                object : CountDownTimer((n2.duration.toLong()), 1000) {
//                    override fun onTick(millisUntilFinished: Long) {
//                        n2.playFreq()
//                        Log.i("note", "onTick method: Playing note ${n2.frequency} for ${n2.duration} milliseconds")
//                    }
//                    override fun onFinish() {
//                        n2.toneObject.stopTune()
//                        Log.i("note", "onFinish method: complete")
//                    }
//                }.start()
//                Log.i("note", "start CountDownTimer")
//            }
//            Log.i("note", "Finished loop")
//        }
//    }

    fun playMelody(view: View) {
        var index = 0
        if (view.id == R.id.button_sound_play) {
            object: CountDownTimer((melodyLength*1000).toLong(), 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    Log.i("note", "note frequency: ${notes[index].frequency}")
                    notes[index].assignFrequency()
                    notes[index].playFreq()
                    startCoolDown(notes[index],1)
                    index += 1
                }
                override fun onFinish() {
                    return
                }
            }.start()
        }
    }

    fun startCoolDown(note: Note, seconds: Int) {
        object: CountDownTimer(seconds*1000.toLong(), seconds*1000.toLong()) {
            override fun onTick(millisUntilFinished: Long) {

            }
            override fun onFinish() {
                note.toneObject.stopTune()
            }
        }.start()
    }

    /**
     * STOP button
     */
//    fun stopTune(view: View) {
//        if (view.id == R.id.button_sound_stop) {
//            //stops the tune
//            perfectTuneTest.stopTune()
//        }
//    }

}
