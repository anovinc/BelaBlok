package com.example.belablok.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.belablok.repositories.AuthenticationRepository

class UserSignOutViewModel(private val authenticationRepository: AuthenticationRepository) : ViewModel() {
    fun signOutUser() {
        authenticationRepository.signOut()
    }
    fun getCurrentUser() {
        authenticationRepository.getUserName()
    }
    fun saveUser() {
    }

}