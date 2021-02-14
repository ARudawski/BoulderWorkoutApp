package com.example.boulderworkoutapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class BoulderDice : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boulder_dice)
    }

    fun startRolling(view: View) {
        val intent = Intent( this, DiceGame::class.java).apply {}
        startActivity(intent)
    }

    fun configBoulderDice(view: View) {
        val intent = Intent( this, ConfigBoulderDice::class.java).apply {}
        startActivity(intent)
    }
}