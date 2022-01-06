package com.example.belablok.repositories

class PlayersRepository {

    private val playersList = mutableListOf<String>()

     //fun addPlayers(players : List<String>) {
       // playersList.addAll(players)
   // }

    fun getPlayers() = playersList
    fun setPlayers(players: List<String>) {
        playersList.addAll(players)
    }
    fun clearPlayers() {
        playersList.clear()
    }
}