package com.example.belablok.repositories

import com.example.belablok.PrefsManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthenticationRepository(private val auth: FirebaseAuth) {

    suspend fun register(email: String, password: String, onResult: (Boolean) -> Unit) {
        withContext(Dispatchers.IO) {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    onResult(true)
                } else {
                    onResult(false)
                }
            }
        }
    }

    suspend fun login(email: String, password: String, onResult: (Boolean) -> Unit) {
        withContext(Dispatchers.IO) {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    onResult(true)
                    PrefsManager().saveUser(getUserName() ?: "")
                } else {
                    onResult(false)
                }
            }
        }
    }

    fun getUserName(): String? {
        val index = auth?.currentUser?.email?.indexOf("@")
        val userName = index?.let { auth.currentUser?.email?.substring(0, it) }
        return userName
    }

    fun signOut() {
        PrefsManager().saveUser("")
        auth.signOut()
    }
}