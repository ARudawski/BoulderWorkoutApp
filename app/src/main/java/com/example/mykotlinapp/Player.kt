package com.example.mykotlinapp

data class Player ( val name: String,
                    val id: Int,
                    var stopwatch: Stopwatch = Stopwatch(),
                    var counter: Int = 0,
                    var active: Boolean = false) {

}