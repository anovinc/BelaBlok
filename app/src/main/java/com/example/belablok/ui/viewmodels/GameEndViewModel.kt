package com.example.belablok.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.belablok.repositories.GameRoundRepository
import com.example.belablok.repositories.PlayersRepository
import kotlinx.coroutines.launch

class GameEndViewModel(
    private val gameRoundRepository: GameRoundRepository,
    private val playersRepository: PlayersRepository,
) : ViewModel() {

    fun clearList() {
        viewModelScope.launch {
            gameRoundRepository.clearList()
        }
    }

    fun clearPlayers() {
            playersRepository.clearPlayers()
    }

}