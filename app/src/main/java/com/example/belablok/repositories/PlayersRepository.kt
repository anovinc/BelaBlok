package com.example.belablok.repositories

import com.example.belablok.data.PrefsManager

class PlayersRepository {

    private val playersList = mutableListOf<String>()

     //fun addPlayers(players : List<String>) {
       // playersList.addAll(players)
   // }

    fun getPlayers() = playersList
    fun setPlayers(players: List<String>) {
        PrefsManager().setLists(players)
        setup()
    }
    fun clearPlayers() {
        PrefsManager().deletePlayers()
        playersList.clear()
    }

    fun setup() {
        playersList.addAll(PrefsManager().getList())
    }
}