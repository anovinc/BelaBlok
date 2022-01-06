package com.example.belablok.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.belablok.repositories.AuthenticationRepository
import kotlinx.coroutines.launch

class UserRegistrationViewModel(private val authenticationRepository: AuthenticationRepository): ViewModel() {
    fun registerNewUser(email: String, password: String, onResult: (Boolean) -> Unit){
        viewModelScope.launch {
            authenticationRepository.register(email,password,onResult)
        }
    }

    fun isMailOrPasswordEmpty(email: String, password: String): Boolean = (email.isNotEmpty() && password.isNotEmpty())
}