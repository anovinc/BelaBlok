package com.example.belablok.model

data class GameViewState(
    var numOfFirstTeamWins: Int = 0,
    var numOfSecondTeamWins: Int = 0,
    var currentDealer: String
) {
    fun increaseNumOfFirstTeamWins() = numOfFirstTeamWins++
    fun increaseNumOfSecondTeamWins() = numOfSecondTeamWins++

}
