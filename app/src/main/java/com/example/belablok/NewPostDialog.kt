package com.example.belablok

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import com.example.belablok.databinding.DialogFragmentNewPostBinding
import com.example.belablok.extensions.invisble
import com.example.belablok.extensions.onClick
import com.example.belablok.extensions.visible
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject

class NewPostDialog : DialogFragment() {
    private val viewModel: NewPostViewModel by inject()
    private lateinit var binding: DialogFragmentNewPostBinding
    val loadImage = registerForActivityResult(
        ActivityResultContracts.GetContent(),
    ) {
        binding.apply {
            ivPost.setImageURI(it)
            btnPost.visible()
            btnPost.onClick {
                viewModel.upload(it)
                dismiss()
            }
        }
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
        }
    }


}