package com.example.mykotlinapp

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.util.Log
import android.widget.*
import java.lang.annotation.ElementType

class ConfigBoulderDice : AppCompatActivity() {

    private val colours = mutableListOf("white", "yellow", "orange", "blue")
    private val disciplines = mutableListOf("speed", "leaps", "feet", "campus")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config_boulder_dice)

        val spColours = getSharedPreferences("Colours", Context.MODE_PRIVATE)
        val editorColours = spColours.edit()

        val spDisciplines = getSharedPreferences("Disciplines", Context.MODE_PRIVATE)
        val editorDisciplines = spDisciplines.edit()

        getData(colours, spColours)
        saveData(colours, editorColours, spColours)

        getData(disciplines, spDisciplines)
        saveData(disciplines, editorDisciplines, spDisciplines)
    }

    private fun saveData(list: List<String>, editor: SharedPreferences.Editor, sp: SharedPreferences) {
        list.forEach { i ->
            findViewById<CompoundButton>(resources.getIdentifier(i, "id", packageName))
                .setOnCheckedChangeListener { _, isChecked ->
                    editor.putBoolean(i, isChecked).apply()
                    println("Following value for '$i' was received: " + sp.getBoolean(i, false))
                }
        }
    }


    private fun getData(list: List<String>, sp: SharedPreferences) {
        list.forEach { i ->
            findViewById<CompoundButton>(resources.getIdentifier(i, "id", packageName))
                .isChecked = sp.getBoolean(i, false)
        }
    }
}