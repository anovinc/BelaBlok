package com.example.belablok

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.belablok.repositories.PostsRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val postsRepository: PostsRepository) : ViewModel() {

    val userPosts = postsRepository.userPosts

    fun getUserPosts() {
        viewModelScope.launch {
            postsRepository.getImages()
        }
    }
}