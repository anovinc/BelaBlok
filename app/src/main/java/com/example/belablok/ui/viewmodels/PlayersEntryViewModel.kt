package com.example.belablok.ui.viewmodels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.belablok.model.PlayerEntryViewState
import com.example.belablok.common.*
import com.example.belablok.repositories.PlayersRepository

class PlayersEntryViewModel(private val playersRepository: PlayersRepository) : ViewModel() {
    private val _playerEntryViewStateLiveData = MutableLiveData(PlayerEntryViewState(PLAYER_ONE, PLAYER_TWO, PLAYER_THREE, PLAYER_FOUR))
    val playerEntryViewStateLiveData: LiveData<PlayerEntryViewState>
        get() = _playerEntryViewStateLiveData

    fun updatePlayerOneName(name: String) {
        _playerEntryViewStateLiveData.value =
            _playerEntryViewStateLiveData.value?.copy(playerOne = if(name.isEmpty()) PLAYER_ONE else name)
    }
    fun updatePlayerTwoName(name: String) {
        _playerEntryViewStateLiveData.value =
            _playerEntryViewStateLiveData.value?.copy(playerTwo = if(name.isEmpty()) PLAYER_TWO else name)
    }
    fun updatePlayerThreeName(name: String) {
        _playerEntryViewStateLiveData.value =
            _playerEntryViewStateLiveData.value?.copy(playerThree = if(name.isEmpty()) PLAYER_THREE else name)
    }
    fun updatePlayerFourName(name: String) {
        _playerEntryViewStateLiveData.value =
            _playerEntryViewStateLiveData.value?.copy(playerFour = if(name.isEmpty()) PLAYER_FOUR else name)
    }
    fun setPlayers(playerList : List<String>) {
        playersRepository.setPlayers(playerList)
    }

}