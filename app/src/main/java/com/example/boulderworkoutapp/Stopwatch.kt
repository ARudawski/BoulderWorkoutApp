package com.example.boulderworkoutapp

import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_dice_game.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class Stopwatch (){
    var time: Long = 0
    var stopwatchRunning = false

     fun startStopwatch(view: TextView){
        stopwatchRunning = true
         val startingTime = System.currentTimeMillis()
         GlobalScope.launch {
             frequentTimer(time, startingTime ,view)
         }
    }

    fun stopStopwatch(){
        stopwatchRunning = false
    }

    private suspend fun frequentTimer(lastStopwatchTime: Long, startingTime: Long, view: TextView) {

        while (stopwatchRunning) {
            time = System.currentTimeMillis() - startingTime
            view.text = formatTime(lastStopwatchTime + time)
            delay(10L)
        }
        time += lastStopwatchTime
    }

    fun formatTime(inputTime: Long): String{
        val milli = ((inputTime).div(10))
            .rem(100).toString().padStart(2, '0')
        val sec = TimeUnit.MILLISECONDS.toSeconds((inputTime))
            .rem(60).toString().padStart(2, '0')
        val min = TimeUnit.MILLISECONDS.toMinutes((inputTime))
            .rem(100).toString().padStart(2, '0')
        return "$min:$sec:$milli"
    }
}