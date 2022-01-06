package com.example.belablok.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.example.belablok.ui.activities.MainActivity
import com.example.belablok.ui.viewmodels.SplashActivityViewModel
import org.koin.android.ext.android.inject

class SplashActivity : AppCompatActivity() {

    private val viewmodel: SplashActivityViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.putExtra("isUserLoggedIn", isUserLoggedIn())
        startActivity(intent)
        finish()
    }

    private fun isUserLoggedIn(): Boolean = viewmodel.isUserLoggedIn()

}