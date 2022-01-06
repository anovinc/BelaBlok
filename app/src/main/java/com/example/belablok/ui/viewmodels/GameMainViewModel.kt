package com.example.belablok.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.belablok.repositories.GameRoundRepository
import com.example.belablok.repositories.PlayersRepository
import com.example.belablok.model.GameViewState

class GameMainViewModel(firstPosition: Int,gameRoundRepository: GameRoundRepository,
                        playersRepository: PlayersRepository) : ViewModel() {
    val gameRoundLiveData = gameRoundRepository.gameRounds
    private var currentDealerPosition: Int
    private val playersList = playersRepository.getPlayers()
    var listSize: Int //Help variable to track is new data in list or is data just edited without changing size
    private val _gameViewStateLiveData = MutableLiveData(GameViewState(0,0,playersList[firstPosition], ))
    val gameViewStateLiveData : LiveData<GameViewState>
        get() = _gameViewStateLiveData

    init {
       currentDealerPosition = firstPosition
       setCurrentDealer()
       listSize = 0
    }

    fun getFirstTeamTotalScore(): Int {

        return gameRoundLiveData.value?.sumOf {
            it.firstTeamScore
        } ?: 0
    }

    fun getSecondTeamTotalScore(): Int {
        return gameRoundLiveData.value?.sumOf {
            it.secondTeamScore
        } ?: 0
    }

    fun setCurrentDealer() {
        _gameViewStateLiveData.value = _gameViewStateLiveData.value?.copy(currentDealer = playersList[currentDealerPosition])
    }

    fun changeCurrentDealer() {
        if (currentDealerPosition < 3) {
            currentDealerPosition++
        } else {
            currentDealerPosition = 0
        }
        setCurrentDealer()
    }

    fun increaseFirstTeamWins() {
       _gameViewStateLiveData.value?.increaseNumOfFirstTeamWins()
    }

    fun increaseSecondTeamWins() {
        _gameViewStateLiveData.value?.increaseNumOfSecondTeamWins()
    }

    fun hasGameEnded(gameEndCount: Int): Boolean =
        (getFirstTeamTotalScore() > gameEndCount || getSecondTeamTotalScore() > gameEndCount)

    fun checkWinner() {
        if(getFirstTeamTotalScore() > getSecondTeamTotalScore()) increaseFirstTeamWins()
        else increaseSecondTeamWins()
    }

    fun getAllData() {

    }

}



