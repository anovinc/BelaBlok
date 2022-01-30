package com.example.belablok.ui.profile

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.belablok.data.PrefsManager
import com.example.belablok.ui.viewmodels.ProfileViewModel
import com.example.belablok.R
import com.example.belablok.ui.profile.adapters.UserPostListAdapter
import com.example.belablok.databinding.FragmentProfileBinding
import com.example.belablok.extensions.onClick
import com.example.belablok.model.Post
import com.example.belablok.ui.base.BaseFragment
import com.example.belablok.ui.game.GameMainFragmentDirections
import org.koin.android.ext.android.inject

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    private val viewmodel : ProfileViewModel by inject()
    private val userPostsAdapter by lazy {
        UserPostListAdapter { post -> deleteOnItemClick(post) }
    }

    private fun deleteOnItemClick(post: Post) {
        val action =
            ProfileFragmentDirections.actionProfileFragmentToDeletePostDialog(post.id)
        findNavController().navigate(action)
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentProfileBinding
        get() = FragmentProfileBinding::inflate


    override fun onPostViewCreated() {
        val user = PrefsManager().getUser()?.replaceFirstChar { it.titlecase() }
        binding.tvUsername.text = user
        binding.btnNewPost.onClick {
            findNavController().navigate(R.id.action_profileFragment_to_newPostDialog)
        }
        setupRecycler()
        viewmodel.getUserPosts()
        observePosts()
    }
    private fun setupRecycler() {
        with(binding.recycler) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = userPostsAdapter
        }
    }

    private fun observePosts() {
        viewmodel.userPosts.observe(viewLifecycleOwner) { posts->
            userPostsAdapter.refreshData(posts)
            Log.i("adapter", "observePosts: $posts")
        }
    }

}