package com.example.belablok.ui.profile.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.belablok.databinding.ItemUserPostBinding
import com.example.belablok.model.GameRound
import com.example.belablok.model.Post

class UserPostListAdapter(private val onItemClicked:(Post) -> Unit): RecyclerView.Adapter<UserPostViewHolder>() {

    private val userPosts: MutableList<Post> = mutableListOf()

    fun refreshData(userPosts: List<Post>) {
        this.userPosts.clear()
        this.userPosts.addAll(userPosts)
        this.notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserPostViewHolder {
        return UserPostViewHolder(ItemUserPostBinding.inflate(LayoutInflater.from(parent.context), parent, false),onItemClicked)
    }

    override fun onBindViewHolder(holder: UserPostViewHolder, position: Int) {
        holder.bind(userPosts[position])
    }

    override fun getItemCount() = userPosts.size
}