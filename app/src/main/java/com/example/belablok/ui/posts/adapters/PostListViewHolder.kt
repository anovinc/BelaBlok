package com.example.belablok.ui.posts.adapters

import androidx.recyclerview.widget.RecyclerView
import com.example.belablok.databinding.ItemUserPostBinding
import com.example.belablok.model.Post
import com.squareup.picasso.Picasso

class PostListViewHolder(private val binding: ItemUserPostBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Post) {
        binding.apply {
            tvPost.text = post.title
            Picasso.get().load(post.imageUrl).fit().centerCrop().into(ivPost)
        }
    }
}