package com.example.belablok.ui.game

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.example.belablok.common.DEFAULT_TOTAL_POINTS
import com.example.belablok.databinding.DialogFragmentNewGameRoundBinding
import com.example.belablok.extensions.onClick
import com.example.belablok.model.GameRound
import com.example.belablok.ui.viewmodels.NewGameRoundViewModel
import org.koin.android.ext.android.inject

class NewGameRoundDialogFragment : DialogFragment() {
    private val args: NewGameRoundDialogFragmentArgs by navArgs()
    private val viewmodel: NewGameRoundViewModel by inject()
    private lateinit var binding: DialogFragmentNewGameRoundBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DialogFragmentNewGameRoundBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initListeners()
    }

    private fun initListeners() {
        with(binding) {
            etFirstTeamScore.setOnFocusChangeListener { view, b ->
                etFirstTeamScore.addTextChangedListener {
                    if (etFirstTeamScore.isFocused) registerFirstTeamResultTextChange(it)

                }
            }

            etSecondTeamScore.setOnFocusChangeListener { view, b ->
                etSecondTeamScore.addTextChangedListener {
                    if (etSecondTeamScore.isFocused) registerSecondTeamResultTextChange(it)
                }
            }

            btnEnterNewRound.onClick {
                checkIsItEditOrSave()
                dismiss()
            }
        }
    }

    private fun saveGameRound() {
        viewmodel.saveGameRound(GameRound(0,
            Integer.parseInt(viewmodel.getFirstTeamScore().toString()),
            Integer.parseInt(viewmodel.getSecondTeamScore().toString())))
    }

    private fun editGameRound() {
        viewmodel.editGameRound(GameRound(getGameRoundId(),
            Integer.parseInt(viewmodel.getFirstTeamScore().toString()),
            Integer.parseInt(viewmodel.getSecondTeamScore().toString())))
    }

    private fun registerFirstTeamResultTextChange(editable: Editable?) {
        if (editable.toString().isNotEmpty()) {
            setDefaultTotalScoreValue()
            setTotalScoreValue(binding.etTotalGameScore.text.toString())
            setFirstTeamScore(Integer.parseInt(editable.toString()))
            calculateSecondResult()
            if (isValueCorrect(Integer.parseInt(editable.toString()),
                    viewmodel.getFirstTeamScore())
            ) binding.etFirstTeamScore.setText(
                viewmodel.getFirstTeamScore().toString())
        } else {
            setDefaultTotalScoreValue()
            setTotalScoreValue(binding.etTotalGameScore.text.toString())
            displayFirstTeamScore(0)
        }
    }

    private fun registerSecondTeamResultTextChange(editable: Editable?) {
        if (editable.toString().isNotEmpty()) {
            setDefaultTotalScoreValue()
            setTotalScoreValue(binding.etTotalGameScore.text.toString())
            setSecondTeamScore(Integer.parseInt(editable.toString()))
            calculateFirstResult()
            if (isValueCorrect(Integer.parseInt(editable.toString()),
                    viewmodel.getSecondTeamScore())
            ) binding.etSecondTeamScore.setText(
                viewmodel.getSecondTeamScore().toString())
        } else {
            setDefaultTotalScoreValue()
            setTotalScoreValue(binding.etTotalGameScore.text.toString())
            displaySecondTeamScore(0)
        }
    }

    private fun isValueCorrect(score: Int, secondValue: Int): Boolean = (score > secondValue)

    private fun setDefaultTotalScoreValue() {
        if (binding.etTotalGameScore.text.isEmpty()) binding.etTotalGameScore.setText(
            DEFAULT_TOTAL_POINTS.toString())
    }

    private fun setTotalScoreValue(score: String) {
        viewmodel.setTotalGameScore(Integer.parseInt(score))
    }

    private fun setFirstTeamScore(score: Int) {
        viewmodel.setFirstTeamScore(score)
    }

    private fun displayFirstTeamScore(score: Int) {
        setFirstTeamScore(score)
        binding.etFirstTeamScore.setText(viewmodel.getFirstTeamScore().toString())
    }

    private fun setSecondTeamScore(score: Int) {
        viewmodel.setSecondTeamScore(score)
    }

    private fun displaySecondTeamScore(score: Int) {
        setSecondTeamScore(score)
        binding.etSecondTeamScore.setText(viewmodel.getSecondTeamScore().toString())
    }

    private fun calculateSecondResult() {
        viewmodel.calculateSecondTeamScore()
        binding.etSecondTeamScore.setText(viewmodel.getSecondTeamScore().toString())
    }

    private fun calculateFirstResult() {
        viewmodel.calculateFirstTeamScore()
        binding.etFirstTeamScore.setText(viewmodel.getFirstTeamScore().toString())
    }

    private fun getGameRoundId() = args.id

    private fun checkIsItEditOrSave() {
        if (getGameRoundId() == 0) saveGameRound()
        else editGameRound()
    }
}





