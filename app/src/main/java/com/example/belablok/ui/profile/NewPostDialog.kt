package com.example.belablok.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.belablok.R
import com.example.belablok.databinding.DialogFragmentNewPostBinding
import com.example.belablok.extensions.invisble
import com.example.belablok.extensions.onClick
import com.example.belablok.extensions.visible
import com.example.belablok.ui.viewmodels.NewPostViewModel
import org.koin.android.ext.android.inject

class NewPostDialog : DialogFragment() {
    private val viewModel: NewPostViewModel by inject()
    private lateinit var binding: DialogFragmentNewPostBinding
    private val loadImage = registerForActivityResult(
        ActivityResultContracts.GetContent(),
    ) {
        binding.apply {
            ivPost.setImageURI(it)
            btnPost.visible()
            btnPost.onClick {
                progressBar.visible()
                viewModel.upload(it) {
                    if(it) {
                        progressBar.invisble()
                        goBack()
                        dismiss()
                        Toast.makeText(context,"Uspješno objavljen post!", Toast.LENGTH_SHORT).show()
                    }
                    else{

                    }
                }
            }

        }
    }

    private fun goBack() {
        findNavController().navigate(R.id.action_newPostDialog_to_profileFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogFragmentNewPostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            btnPost.invisble()
            btnAddPhoto.onClick {
                loadImage.launch("image/*")
            }
            btnCancel.onClick {
                dismiss()
            }
        }
    }
    override fun onStart() {
        super.onStart()
        isCancelable = false
    }
}