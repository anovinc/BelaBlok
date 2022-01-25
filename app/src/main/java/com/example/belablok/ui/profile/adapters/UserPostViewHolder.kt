package com.example.belablok.ui.profile.adapters

import android.view.View
import android.view.View.*
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.belablok.databinding.ItemUserPostBinding
import com.example.belablok.extensions.invisble
import com.example.belablok.extensions.onClick
import com.example.belablok.extensions.visible
import com.example.belablok.model.GameRound
import com.example.belablok.model.Post
import com.squareup.picasso.Picasso
import java.net.URI

class UserPostViewHolder(
    private val binding: ItemUserPostBinding,
    private val onItemClicked: (Post) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Post) {
        binding.apply {
            tvPost.text = post.title
            Picasso.get().load(post.imageUrl).fit().centerCrop().into(ivPost)
        }
        binding.root.onClick {
            if (!binding.delete.isVisible) {
                binding.delete.visible()
                binding.tvPerson.invisble()
            } else {
                binding.delete.invisble()
                binding.tvPerson.visible()

            }
        }
        binding.delete.onClick {
            onItemClicked(post)

        }
    }

}


