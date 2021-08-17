package com.example.android.guesstheword.screens.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

// vibration patterns, longArrayOf(wait for, buzz for, wait for, buzz for)
private val CORRECT_BUZZ_PATTERN = longArrayOf(100, 100, 100, 100, 100, 100)
private val PANIC_BUZZ_PATTERN = longArrayOf(0, 200)
private val GAME_OVER_BUZZ_PATTERN = longArrayOf(0, 2000)
private val NO_BUZZ_PATTERN = longArrayOf(0)

class GameViewModel : ViewModel() {

    companion object {
        // These represent different important times
        // This is when the game is over
        const val DONE = 0L
        // This is the number of milliseconds in a second
        const val ONE_SECOND = 1000L
        // This is the total time of the game
        const val COUNTDOWN_TIME = 60000L
    }

    // different types of buzz patterns
    enum class BuzzType(val pattern: LongArray) {
        CORRECT(CORRECT_BUZZ_PATTERN),
        GAME_OVER(GAME_OVER_BUZZ_PATTERN),
        COUNTDOWN_PANIC(PANIC_BUZZ_PATTERN),
        NO_BUZZ(NO_BUZZ_PATTERN)
    }

    private val timer: CountDownTimer

    // The current time
    // live data A
    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime

    // live data B, output strings
    val currentTimeString = Transformations.map(currentTime, { time ->
        DateUtils.formatElapsedTime(time)
    })

    // The current word
    private var _word = MutableLiveData<String>()
    val word : LiveData<String>
        get() = _word

    // The current score
    // Internal version
    private var _score = MutableLiveData<Int>()
    // require null safety checks
    // External version available public
    val score : LiveData<Int>
        get() = _score

    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>

    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish

    init {
        _eventGameFinish.value = false
        Log.i("GameViewModel", "GameViewModel created!")
        resetList()
        nextWord()
        _score.value = 0
        // Creates a timer which triggers the end of the game when it finishes
        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = (millisUntilFinished / ONE_SECOND)
            }
            override fun onFinish() {
                // implement what should happen when the timer finishes
                _currentTime.value = DONE
                _eventGameFinish.value = true
            }
        }
        // You'll need DateUtils.formatElapsedTime()

        timer.start()
    }

    /**
     * Resets the list of words and randomizes the order
     */
    private fun resetList() {
        wordList = mutableListOf(
            "Loki",
            "Thor",
            "Black Widow",
            "Iron Man",
            "Captain America",
            "Hawkeye",
            "Antman",
            "Spider-Man",
            "Hulk",
            "Falcon",
            "Winter Soldier",
            "War Machine",
            "Valkyrie",
            "Star Lord",
            "Rocket Raccoon",
            "Gamora",
            "Groot",
            "Drax",
            "Nebula",
            "Mantis",
            "Vision",
            "Scarlet Witch"
        )
        wordList.shuffle()
    }

    /**
     * Moves to the next word in the list
     */
    private fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
//            gameFinished()
//            _eventGameFinish.value = true
            resetList()
        }
        // else
        _word.value = wordList.removeAt(0)

        // Can remove because we call them in the methods in onCreate
//        updateWordText()
//        updateScoreText()
    }

    fun onSkip() {
        _score.value = (score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _score.value = (score.value)?.plus(1)
        nextWord()
    }

    fun onGameFinishComplete() {
        _eventGameFinish.value = false
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
        Log.i("GameViewModel", "GameViewModel destroyed!")
    }

    // LIVE DATA = An observable data holder class that is lifecycle-aware
}