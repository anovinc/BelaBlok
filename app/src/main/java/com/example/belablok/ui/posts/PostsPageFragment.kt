package com.example.belablok.ui.posts

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.belablok.databinding.FragmentPostsPageBinding
import com.example.belablok.ui.base.BaseFragment
import com.example.belablok.ui.posts.adapters.PostListViewHolder
import com.example.belablok.ui.posts.adapters.PostsListAdpater
import com.example.belablok.ui.profile.adapters.UserPostListAdapter
import com.example.belablok.ui.viewmodels.PostsPageViewModel
import org.koin.android.ext.android.inject

class PostsPageFragment : BaseFragment<FragmentPostsPageBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPostsPageBinding
        get() = FragmentPostsPageBinding::inflate
    private val postsAdapter by lazy {
        PostsListAdpater()
    }
    private val viewmodel : PostsPageViewModel by inject()
    override fun onPostViewCreated() {
        setupRecycler()
        observePosts()
    }
    private fun setupRecycler() {
        with(binding.recycler) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = postsAdapter
        }
    }

    private fun observePosts() {
        viewmodel.getUserPosts()
        viewmodel.userPosts.observe(viewLifecycleOwner) { posts->
            postsAdapter.refreshData(posts)
            Log.i("adapter", "observePosts: $posts")
        }
    }
}