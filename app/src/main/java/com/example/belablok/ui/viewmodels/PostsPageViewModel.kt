package com.example.belablok.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.belablok.model.Post
import com.example.belablok.repositories.PostsRepository
import kotlinx.coroutines.launch

class PostsPageViewModel(private val postsRepository: PostsRepository) : ViewModel() {
    private val _posts = MutableLiveData<List<Post>>()
    val userPosts: LiveData<List<Post>>
        get() = _posts

    fun getUserPosts() {
        viewModelScope.launch {
            _posts.value = postsRepository.getAllPosts()
        }
    }
}