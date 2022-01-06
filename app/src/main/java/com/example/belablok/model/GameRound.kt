package com.example.belablok.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gameRounds")
data class GameRound(
    @PrimaryKey
    var id: Int,
    var firstTeamScore: Int,
    var secondTeamScore: Int,
)
