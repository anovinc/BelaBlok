package com.example.belablok.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.belablok.repositories.GameRoundRepository
import kotlinx.coroutines.launch

class GameSelectionViewModel(private val gameRoundRepository: GameRoundRepository) : ViewModel() {
    fun clearData() {
        viewModelScope.launch {
            gameRoundRepository.clearList()
        }
    }
}