package com.example.belablok.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.belablok.PrefsManager
import com.example.belablok.ProfileViewModel
import com.example.belablok.R
import com.example.belablok.UserPostListAdapter
import com.example.belablok.databinding.FragmentProfileBinding
import com.example.belablok.extensions.onClick
import com.example.belablok.ui.base.BaseFragment
import org.koin.android.ext.android.inject

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    private val viewmodel : ProfileViewModel by inject()
    private val userPostsAdapter by lazy {
        UserPostListAdapter()
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
        observePosts()
    }
    private fun setupRecycler() {
        with(binding.recycler) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = userPostsAdapter
        }
    }

    private fun observePosts() {
        viewmodel.userPosts.observe(viewLifecycleOwner) {
            userPostsAdapter.refreshData(it)
        }
    }



}