package com.example.mykotlinapp

class PlayerService {
    var myPlayers = mutableSetOf<Player>()

    fun addPlayer(name: String, id: Int)  {myPlayers.add(Player(name, id))
    println("player " + myPlayers.last() + "was added")}

    fun deletePlayer(id: Int) = myPlayers.forEach{i ->
        if (id == i.id){myPlayers.remove(i)}
        println("player $i was deleted")
    }

    fun getPlayer(id: Int): Player? {
        var output: Player? = null
        myPlayers.forEach { i ->
            if (id == i.id) {
                output = i
            }
            println("player $i was was taken")
        }
        return output
    }

    fun getActivePlayer(): Player? {
        var output: Player? = null
        myPlayers.forEach{i ->
            if (i.active){
                output = i
            }
            println("player $i was was taken")
        }
        return output
    }

    fun setActivePlayer(player: Player) {
        getActivePlayer()?.stopwatch?.stopStopwatch()
        myPlayers.forEach {i ->
            i.active = player == i
        }
    }

    fun getActiveTime():String?{
       return getActivePlayer()?.stopwatch?.time?.let { getActivePlayer()?.stopwatch?.formatTime(it) }
    }

    fun getSize(): Int {
        return myPlayers.size
    }
}