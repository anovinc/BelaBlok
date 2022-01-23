package com.example.belablok.ui.viewmodels

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.belablok.repositories.PostsRepository
import kotlinx.coroutines.launch

class NewPostViewModel(private val postsRepository: PostsRepository) : ViewModel() {
     fun upload(fileUrl: Uri) {
        viewModelScope.launch {
            postsRepository.uploadImage(fileUrl)
        }
    }
}