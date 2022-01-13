package com.example.belablok.ui.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.belablok.PrefsManager
import com.example.belablok.R
import com.example.belablok.R.id.*
import com.example.belablok.databinding.FragmentDrawerHostBinding
import com.example.belablok.extensions.invisible
import com.example.belablok.extensions.onClick
import com.example.belablok.ui.base.BaseFragment

class DrawerHostFragment : BaseFragment<FragmentDrawerHostBinding>() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDrawerHostBinding
        get() = FragmentDrawerHostBinding::inflate


    override fun onPostViewCreated() {
        setupNavigationDrawer()
        setupNavController()
        setupNavigationActions()
        isUserLoggedIn()
        initializeListeners()
    }

    private fun initializeListeners() {
        binding.navView.getHeaderView(0).findViewById<TextView>(tv_username).onClick {
            goToProfile()
        }
    }

    private fun setupNavigationDrawer() {
        drawerLayout = binding.drawerLayout
        appBarConfiguration = AppBarConfiguration(setOf(gameSelectionFragment), drawerLayout)
        toolbar = binding.toolbar
    }

    private fun setupNavController() {
        val navHostFragment =
            childFragmentManager.findFragmentById(nav_host_drawer_fragment) as NavHostFragment
        navController = navHostFragment.navController
        toolbar.setupWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
    }

    private fun goToLogin() {
        val navController = activity?.findNavController(nav_host_fragment)
        navController?.navigate(action_drawerHostFragment_to_userLoginFragment)
    }

    private fun setupNavigationActions() {
        binding.navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                gameSelectionFragment -> {
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                login -> {
                    goToLogin()
                    true
                }
                signOut -> {
                    goToSignOut()
                    true
                }
                rulesFragment -> {
                    goToRules()
                    true
                }
                else -> false
            }

        }

    }

    private fun goToRules() {
        val navController = activity?.findNavController(nav_host_drawer_fragment)
        navController?.navigate(rulesFragment)
        drawerLayout.closeDrawer(GravityCompat.START)
    }

    private fun goToSignOut() {
        val navController = activity?.findNavController(nav_host_fragment)
        navController?.navigate(action_drawerHostFragment_to_userSignOutDialogFragment)
    }

    private fun isUserLoggedIn() {
        if (PrefsManager().getUser().isNullOrEmpty()) {
            hideMenuItem(signOut)
            hideMenuItem(aa)
        } else {
            showUsername()
            hideMenuItem(login)
        }
    }

    private fun hideMenuItem(id: Int) {
        val menu = binding.navView.menu
        menu.findItem(id).invisible()
    }

    private fun showUsername() {
        val user = PrefsManager().getUser()?.replaceFirstChar { it.titlecase() }
        val header = binding.navView.getHeaderView(0)
        header.findViewById<TextView>(tv_username).text = user
    }

    private fun goToProfile() {
        val navController = activity?.findNavController(nav_host_fragment)
        navController?.navigate(action_drawerHostFragment_to_profileFragment)
    }

}