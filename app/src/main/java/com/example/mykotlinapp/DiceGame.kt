package com.example.mykotlinapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_dice_game.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.IllegalStateException
import java.lang.IndexOutOfBoundsException
import java.lang.NullPointerException
import java.lang.System.currentTimeMillis
import java.util.concurrent.TimeUnit

class DiceGame : AppCompatActivity() {

    var colours = mutableListOf<Any?>()
    var disciplines = mutableListOf<Any?>()
    var myPlayers = PlayerService()
    private var active = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dice_game)

        colours = getActivePrefs("Colours")
        Log.d(colours.toString(), "received")

        disciplines = getActivePrefs("Disciplines")
        Log.d(disciplines.toString(), "received")
    }

    private fun getActivePrefs(prefKey: String) =
        getSharedPreferences(prefKey, Context.MODE_PRIVATE)
            .all
            .filter { it.value == true }
            .keys.toMutableList<Any?>()

    @SuppressLint("SetTextI18n")
    fun resetTimer(view: View) {
        try {
            active = false
            myPlayers.getActivePlayer()?.stopwatch?.time = 0
            myPlayers.getActivePlayer()?.counter = 0
            Timer.text = "00:00:00"
            Counter.text = "0"
        } catch (e: IndexOutOfBoundsException) {}
    }

    @SuppressLint("SetTextI18n")
    fun useCounter(view: View) {
        myPlayers.getActivePlayer()?.counter = myPlayers.getActivePlayer()?.counter!! + 1
        Counter.text =  myPlayers.getActivePlayer()?.counter.toString()
    }

    fun useStopwatch(view: View){
        if(!myPlayers.getActivePlayer()?.stopwatch?.stopwatchRunning!!){
            myPlayers.getActivePlayer()?.stopwatch?.startStopwatch(Timer)
        }else{
            myPlayers.getActivePlayer()?.stopwatch?.stopStopwatch()
        }
    }

    private fun rollDices(dice1: TextView, dice2: TextView) {
        dice1.text = colours
            .shuffled()
            .firstOrNull()
            .toString()
        dice2.text = disciplines
            .shuffled()
            .firstOrNull()
            .toString()
    }

    fun diceEvent(view: View) {
        resetTimer(reset)
        rollDices(dice1, dice2)
        groupVisibility(dice2.text.toString())
    }

    private fun groupVisibility(event: String){
        when (event) {
            "speed" -> {
                countGroup.visibility = View.INVISIBLE
                speedGroup.visibility = View.VISIBLE
            }
            "leaps", "campus" -> {
                speedGroup.visibility = View.INVISIBLE
                countGroup.visibility = View.VISIBLE
            }
        }
    }

    fun setPlayer(view: View) {
        active = false
        val myButton = findViewById<Button>(view.id)
        println("myButton text is:" + myButton.text)

        myPlayers.getPlayer(myButton.id)?.let { myPlayers.setActivePlayer(it) }

        if (speedGroup.visibility == View.VISIBLE){
            Timer.text = myPlayers.getActiveTime()
        }
        else if(countGroup.visibility == View.VISIBLE){
            Counter.text =  myPlayers.getActivePlayer()?.counter.toString()
        }
    }

    fun addPlayer(view: View) {
        popupDialog()
    }

    private fun popupDialog (){
            val builder = AlertDialog.Builder(this)
            val inflater = layoutInflater
            builder.setTitle("With EditText")
            val dialogLayout = inflater.inflate(R.layout.edit_text_popup, null)
            val editText = dialogLayout.findViewById<EditText>(R.id.editText)
            builder.setView(dialogLayout)
            builder.setPositiveButton("OK") { _, _ ->
                findViewById<Button>(
                    resources.getIdentifier(
                        "player" + (myPlayers.getSize() + 1),
                        "id",
                        packageName
                    )
                )
                    .text = editText.text
                    myPlayers.addPlayer(editText.text.toString(),  resources.getIdentifier(
                      "player" + (myPlayers.getSize() + 1),
                      "id",
                      packageName
                    )
                )
            }
        builder.show()
    }
}