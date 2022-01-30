package com.example.belablok.ui.profile.adapters


import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.belablok.databinding.ItemProfilePostBinding
import com.example.belablok.extensions.invisble
import com.example.belablok.extensions.onClick
import com.example.belablok.extensions.visible
import com.example.belablok.model.Post
import com.squareup.picasso.Picasso

class UserPostViewHolder(
    private val binding: ItemProfilePostBinding,
    private val onItemClicked: (Post) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Post) {
        binding.apply {
            Picasso.get().load(post.imageUrl).fit().centerCrop().into(ivPost)
        }
        binding.root.onClick {
            if (!binding.delete.isVisible) {
                binding.delete.visible()
            } else {
                binding.delete.invisble()

            }
        }
        binding.delete.onClick {
            onItemClicked(post)

        }
    }

}


