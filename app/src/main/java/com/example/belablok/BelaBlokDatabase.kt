package com.example.belablok

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.belablok.model.GameRound

@Database(entities = [GameRound::class], version = 1, exportSchema = false)
abstract class BelaBlokDatabase : RoomDatabase() {
    abstract fun gameRoundDao(): GameRoundDao
}