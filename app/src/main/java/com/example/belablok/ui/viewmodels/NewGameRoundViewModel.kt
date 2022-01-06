package com.example.belablok.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.belablok.common.DEFAULT_TOTAL_POINTS
import com.example.belablok.repositories.GameRoundRepository
import com.example.belablok.model.GameRound
import kotlinx.coroutines.launch

class NewGameRoundViewModel(private val gameRoundRepository: GameRoundRepository) : ViewModel() {

    private var firstTeamScore: Int
    private var secondTeamScore: Int
    private var totalGameScore: Int = DEFAULT_TOTAL_POINTS

    init {
        firstTeamScore = 0
        secondTeamScore = 0
    }

    fun calculateSecondTeamScore() {
        secondTeamScore = totalGameScore - firstTeamScore
    }

    fun calculateFirstTeamScore() {
        firstTeamScore = totalGameScore - secondTeamScore
    }

    fun setFirstTeamScore(score: Int) {
        if (score > totalGameScore) firstTeamScore = totalGameScore
        else firstTeamScore = score
    }

    fun setSecondTeamScore(score: Int) {
        if (score > totalGameScore) secondTeamScore = totalGameScore
        else secondTeamScore = score
    }

    fun setTotalGameScore(score: Int) {
        totalGameScore = score
    }

    fun saveGameRound(gameRound: GameRound) {
        viewModelScope.launch {
            gameRound.id = getGameRoundsSize() + 1
            gameRoundRepository.addGameRound(gameRound)
        }
    }

    fun getGameRoundsSize(): Int {
        return gameRoundRepository.gameRounds.value?.size ?: 0
    }

    fun getFirstTeamScore() = firstTeamScore
    fun getSecondTeamScore() = secondTeamScore

    fun editGameRound(gameRound: GameRound) {
        viewModelScope.launch {
            gameRoundRepository.editGameRound(gameRound)
        }
    }

}