package com.example.belablok.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.belablok.data.PrefsManager

class SplashActivityViewModel() : ViewModel() {
    fun isUserLoggedIn() : Boolean {
        return !PrefsManager().getUser().isNullOrEmpty()
    }


}