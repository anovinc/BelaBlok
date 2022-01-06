package com.example.belablok.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.belablok.repositories.AuthenticationRepository
import kotlinx.coroutines.launch

class UserSignUpViewModel(private val authenticationRepository: AuthenticationRepository) : ViewModel() {
    fun loginUser(email: String, password: String, onResult: (Boolean) -> Unit){
        viewModelScope.launch {
            authenticationRepository.login(email,password,onResult)
        }
    }
    fun getCurrentUser(): String? = authenticationRepository.getUserName()
    fun isMailOrPasswordEmpty(email: String, password: String): Boolean = (email.isNotEmpty() && password.isNotEmpty())
}