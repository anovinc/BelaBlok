package com.example.belablok.ui.game_selection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.belablok.data.PrefsManager
import com.example.belablok.R
import com.example.belablok.databinding.FragmentGameSelectionBinding
import com.example.belablok.extensions.invisble
import com.example.belablok.ui.base.BaseFragment
import com.example.belablok.extensions.onClick
import com.example.belablok.ui.viewmodels.GameSelectionViewModel
import org.koin.android.ext.android.inject

class GameSelectionFragment : BaseFragment<FragmentGameSelectionBinding>() {

    private val viewmodel : GameSelectionViewModel by inject()
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGameSelectionBinding
        get() = FragmentGameSelectionBinding::inflate

    private fun startGame() {
        val navController = activity?.findNavController(R.id.nav_host_fragment)
        navController?.navigate(R.id.action_drawerHostFragment_to_playersEntryFragment)
    }

    private fun continueGame() {
        val navController = activity?.findNavController(R.id.nav_host_fragment)
        navController?.navigate(R.id.action_drawerHostFragment_to_gameMainFragment)
    }

    private fun initListeners() {
        binding.apply {
            btnNewGame.onClick {
                clearData()
                startGame()
            }
            btnContinue.onClick {
               // continueGame()
            }
        }
    }

    override fun onPostViewCreated() {
        initListeners()
        hideContinueButton()
    }

    private fun isUserLoggedIn(): Boolean {
        return !PrefsManager().getUser().isNullOrEmpty()
    }
    private fun hideContinueButton() {
        if(!isUserLoggedIn()) binding.btnContinue.invisble()
    }
    private fun clearData() {
        viewmodel.clearData()
    }

}