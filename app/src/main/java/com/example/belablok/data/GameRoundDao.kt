package com.example.belablok.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.example.belablok.model.GameRound

@Dao
interface GameRoundDao {
    @Query("SELECT * FROM gameRounds")
    suspend fun getAll(): List<GameRound>

    @Query("DELETE FROM gameRounds")
    suspend fun clearAll()

    @Insert(onConflict = REPLACE)
    suspend fun insert(gameRound: GameRound)

    @Query("UPDATE gameRounds SET firstTeamScore= :firstTeamScore, secondTeamScore= :secondTeamScore WHERE id= :id ")
    suspend fun edit(id: Int, firstTeamScore: Int, secondTeamScore: Int)

}