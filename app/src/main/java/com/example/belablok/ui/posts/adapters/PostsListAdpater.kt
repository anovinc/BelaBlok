package com.example.belablok.ui.posts.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.belablok.databinding.ItemUserPostBinding
import com.example.belablok.model.Post


class PostsListAdpater : RecyclerView.Adapter<PostListViewHolder>() {

    private val postList: MutableList<Post> = mutableListOf()

    fun refreshData(postsList: List<Post>) {
        this.postList.clear()
        this.postList.addAll(postsList)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListViewHolder {
        return PostListViewHolder(
            ItemUserPostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun getItemCount() = postList.size
    override fun onBindViewHolder(holder: PostListViewHolder, position: Int) {
        holder.bind((postList[position]))
    }
}