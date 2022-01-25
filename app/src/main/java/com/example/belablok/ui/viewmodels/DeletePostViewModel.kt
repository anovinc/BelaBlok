package com.example.belablok.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.belablok.repositories.PostsRepository
import kotlinx.coroutines.launch

class DeletePostViewModel(private val postsRepository: PostsRepository) : ViewModel() {
    fun deletePost(id: Int) {
        viewModelScope.launch {
            postsRepository.deletePost(id)
        }
    }
}