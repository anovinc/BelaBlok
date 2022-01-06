package com.example.belablok.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.belablok.GameRoundDao
import com.example.belablok.model.GameRound
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GameRoundRepository(private val gameRoundDao: GameRoundDao) {

    private val initialList: MutableList<GameRound> = mutableListOf()

    private val _gameRounds = MutableLiveData<List<GameRound>>(initialList)
    val gameRounds: LiveData<List<GameRound>>
        get() = _gameRounds

    suspend fun addGameRound(gameRound: GameRound) {
        withContext(Dispatchers.IO) {
            gameRoundDao.insert(gameRound)
            setAll()
        }
    }

    suspend fun editGameRound(gameRound: GameRound) {
        withContext(Dispatchers.IO) {
            gameRoundDao.edit(gameRound.id, gameRound.firstTeamScore, gameRound.secondTeamScore)
            setAll()
        }
    }

    suspend fun clearList() {
        withContext(Dispatchers.IO) {
            gameRoundDao.clearAll()
            setAll()
        }
    }

    suspend fun setAll() {
        withContext(Dispatchers.IO) {
            initialList.clear()
            initialList.addAll(gameRoundDao.getAll())
            _gameRounds.postValue(initialList)
        }
    }

}