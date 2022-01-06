package com.example.belablok.ui.game.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.belablok.databinding.ItemRoundBinding
import com.example.belablok.model.GameRound

class GameRoundListAdapter(private val onItemClicked:(GameRound) -> Unit): RecyclerView.Adapter<GameRoundViewHolder>() {

    private val gameRoundList: MutableList<GameRound> = mutableListOf()

     fun refreshData(gameRoundList: List<GameRound>) {
        this.gameRoundList.clear()
        this.gameRoundList.addAll(gameRoundList)
        this.notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameRoundViewHolder {
      return  GameRoundViewHolder(ItemRoundBinding.inflate(LayoutInflater.from(parent.context),parent,false),onItemClicked)
    }

    override fun onBindViewHolder(holder: GameRoundViewHolder, position: Int) {
        holder.bind(gameRoundList[position])

        
    }

    override fun getItemCount() = gameRoundList.size

}