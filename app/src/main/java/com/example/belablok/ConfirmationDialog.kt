package com.example.belablok

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.belablok.databinding.DialogConfirmationBinding
import com.example.belablok.extensions.onClick

class ConfirmationDialog : DialogFragment() {

    private lateinit var binding: DialogConfirmationBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogConfirmationBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun initializeListeners() {
        with(binding) {
            tvAccept.onClick { activity?.finishAndRemoveTask()}
            tvCancel.onClick { dismiss() }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initializeListeners()
    }
}