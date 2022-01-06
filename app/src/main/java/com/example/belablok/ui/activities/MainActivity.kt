package com.example.belablok.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavHost
import androidx.navigation.NavInflater
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.belablok.R
import java.util.zip.Inflater

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_BelaBlok)
        setContentView(R.layout.activity_main)
        setNavigationGraph()
    }

    private fun setNavigationGraph() {
        val isUserLoggedIn = intent.getBooleanExtra("isUserLoggedIn", false)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val navGraph = navController.navInflater.inflate(R.navigation.navigation)
        if(isUserLoggedIn) {
            navGraph.startDestination = R.id.drawerHostFragment
        }
        else {
            navGraph.startDestination = R.id.userLoginFragment
        }
        navController.graph = navGraph
    }

}

