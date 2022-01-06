package com.example.belablok.ui.players


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.belablok.R
import com.example.belablok.databinding.FragmentPlayersEntryBinding
import com.example.belablok.extensions.onClick
import com.example.belablok.ui.base.BaseFragment
import com.example.belablok.ui.viewmodels.PlayersEntryViewModel
import org.koin.android.ext.android.bind
import org.koin.android.ext.android.inject

class PlayersEntryFragment : BaseFragment<FragmentPlayersEntryBinding>() {
    private val viewModel : PlayersEntryViewModel by inject()
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPlayersEntryBinding
        get() = FragmentPlayersEntryBinding::inflate

    override fun onPostViewCreated() {
        initListeners()
        registerTextChange()

    }

    private fun initListeners() {
        binding.apply {
            btnStart.onClick {
                startGame()
            }
            etPlayer1.addTextChangedListener { viewModel.updatePlayerOneName(it.toString()) }
            etPlayer2.addTextChangedListener { viewModel.updatePlayerTwoName(it.toString()) }
            etPlayer3.addTextChangedListener { viewModel.updatePlayerThreeName(it.toString()) }
            etPlayer4.addTextChangedListener { viewModel.updatePlayerFourName(it.toString()) }
        }
    }

    private fun startGame() {
        val action = PlayersEntryFragmentDirections.actionPlayersEntryFragmentToGameMainFragment(getGameEndCount(),getFirstDealer())
        findNavController().navigate(action)
        viewModel.setPlayers(getPlayers())
    }

    private fun getGameEndCount() = when(binding.rgGameEndingCount.checkedRadioButtonId) {
        R.id.rbtn_501 -> 501
        R.id.rbtn_701 -> 701
        R.id.rbtn_1001-> 1001
        else -> 1001
    }

    private fun getFirstDealer() = when(binding.rgDealer.checkedRadioButtonId) {
        R.id.rbtn_player1 -> 0
        R.id.rbtn_player2 -> 1
        R.id.rbtn_player3 -> 2
        R.id.rbtn_player4 -> 3
        else -> 0
    }

    private fun registerTextChange() {
        viewModel.playerEntryViewStateLiveData.observe(viewLifecycleOwner) {
            binding.rbtnPlayer1.text = it.playerOne
            binding.rbtnPlayer2.text = it.playerTwo
            binding.rbtnPlayer3.text = it.playerThree
            binding.rbtnPlayer4.text = it.playerFour
        }
    }

    private fun getPlayers() : List<String> = listOf(binding.rbtnPlayer1.text.toString(),binding.rbtnPlayer2.text.toString(), binding.rbtnPlayer3.text.toString(), binding.rbtnPlayer4.text.toString() )

}
