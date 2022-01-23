package com.example.belablok.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.belablok.model.Post
import com.example.belablok.repositories.PostsRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val postsRepository: PostsRepository) : ViewModel() {


    private val _userPosts = MutableLiveData<List<Post>>()
    val userPosts: LiveData<List<Post>>
        get() = _userPosts

    fun getUserPosts() {
        viewModelScope.launch {
            _userPosts.value = postsRepository.getUserPosts()
        }
    }
}