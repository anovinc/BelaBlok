package com.example.belablok.ui.game

import android.media.SoundPool
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.belablok.R
import com.example.belablok.data.PrefsManager
import com.example.belablok.ui.game.adapters.GameRoundListAdapter
import com.example.belablok.databinding.FragmentGameMainBinding
import com.example.belablok.extensions.onClick
import com.example.belablok.model.GameRound
import com.example.belablok.ui.base.BaseFragment
import com.example.belablok.ui.viewmodels.GameMainViewModel
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class GameMainFragment : BaseFragment<FragmentGameMainBinding>() {
    private val args: GameMainFragmentArgs by navArgs()
    private val gameRoundListAdapter by lazy {
        GameRoundListAdapter { gameRound ->
            editOnGameRoundClickListener(gameRound)
        }
    }
    private val viewmodel: GameMainViewModel by inject { parametersOf(args.firstDealer) }
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGameMainBinding
        get() = FragmentGameMainBinding::inflate

    override fun onPostViewCreated() {
        with(binding) {
            tvGameEndingCount.text = getGameEndCount().toString()
        }
        setupRecycler()
        initListeners()
        observeGameRounds()
        observeGameViewState()
        requireActivity().onBackPressedDispatcher.addCallback(this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.action_gameMainFragment_to_confirmationDialog)
                }


            })

    }


    private fun setupRecycler() {
        with(binding.recycler) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = gameRoundListAdapter
        }
    }

    private fun initListeners() {
        with(binding) {
            btnNewRound.onClick { addNewGameRound() }
            tvFirstTeamWins.onClick {
                Toast.makeText(
                    context,
                    "Broj pobjeda MI tima",
                    Toast.LENGTH_SHORT
                ).show()
            }
            tvSecondTeamWins.onClick {
                Toast.makeText(
                    context,
                    "Broj pobjeda VI tima",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun observeGameRounds() {
        viewmodel.gameRoundLiveData.observe(viewLifecycleOwner) { gameRoundList ->
            gameRoundListAdapter.refreshData(gameRoundList)
            if (gameRoundList.isEmpty()) viewmodel.setCurrentDealer()
            else if (gameRoundList.size + 1 != viewmodel.listSize) viewmodel.changeCurrentDealer()
            with(binding) {
                if (viewmodel.hasGameEnded(getGameEndCount())) goToEndGameScreen()

                tvFirstTeamResult.text = viewmodel.getFirstTeamTotalScore().toString()
                tvSecondTeamResult.text = viewmodel.getSecondTeamTotalScore().toString()
            }
        }
    }

    private fun observeGameViewState() {
        viewmodel.gameViewStateLiveData.observe(viewLifecycleOwner) {
            viewmodel.listSize++
            binding.tvCurrentDealerName.text = it.currentDealer
            binding.tvFirstTeamWins.text = it.numOfFirstTeamWins.toString()
            binding.tvSecondTeamWins.text = it.numOfSecondTeamWins.toString()

        }
    }

    private fun getGameEndCount() = args.gameEndCount

    private fun addNewGameRound() {
        findNavController().navigate(R.id.action_gameMainFragment_to_newGameRoundDialogFragment)
    }


    private fun goToEndGameScreen() {
        val action =
            NewGameRoundDialogFragmentDirections.actionNewGameRoundDialogFragmentToGameEndFragment(
                viewmodel.getFirstTeamTotalScore(),
                viewmodel.getSecondTeamTotalScore()
            )
        findNavController().navigate(action)
        viewmodel.checkWinner()
    }

    private fun editOnGameRoundClickListener(gameRound: GameRound) {
        val action =
            GameMainFragmentDirections.actionGameMainFragmentToNewGameRoundDialogFragment(gameRound.id)
        findNavController().navigate(action)
    }


}


