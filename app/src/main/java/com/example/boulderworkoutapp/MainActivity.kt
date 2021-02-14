package com.example.boulderworkoutapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startBoulderDice(view: View) {
        val intent = Intent( this, BoulderDice::class.java).apply {}
        startActivity(intent)
    }
}