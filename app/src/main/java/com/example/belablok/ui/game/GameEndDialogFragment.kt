package com.example.belablok.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.belablok.R
import com.example.belablok.databinding.DialogFragmentGameEndBinding
import com.example.belablok.extensions.onClick
import com.example.belablok.ui.viewmodels.GameEndViewModel
import org.koin.android.ext.android.inject

class GameEndDialogFragment : DialogFragment() {
    private val viewmodel: GameEndViewModel by inject()
    private lateinit var binding: DialogFragmentGameEndBinding
    private val args: GameEndDialogFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DialogFragmentGameEndBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            btnNewGame.onClick {
                viewmodel.clearList()
                viewmodel.clearPlayers()
                findNavController().navigate(R.id.action_gameEndFragment_to_playersEntryFragment)
                dismiss()
            }
            btnContinue.onClick {
                viewmodel.clearList()
                dismiss()
            }
            tvFirstTeamScore.text = args.firstTeamScore.toString()
            tvSecondTeamScore.text = args.secondTeamScore.toString()
        }

    }

    override fun onStart() {
        super.onStart()
        isCancelable = false
    }
}