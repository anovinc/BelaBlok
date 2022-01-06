package com.example.belablok.ui.game.adapters

import androidx.recyclerview.widget.RecyclerView
import com.example.belablok.extensions.onClick
import com.example.belablok.databinding.ItemRoundBinding

import com.example.belablok.model.GameRound

class GameRoundViewHolder(private val binding: ItemRoundBinding, private val onItemClicked:(GameRound) -> Unit) : RecyclerView.ViewHolder(binding.root) {

    fun bind(gameRound: GameRound) {
        binding.apply {
            tvFirstTeamRoundScore.text = gameRound.firstTeamScore.toString()
            tvSecondTeamRoundScore.text = gameRound.secondTeamScore.toString()
        }
        binding.root.onClick {
            onItemClicked(gameRound)
        }
    }
}