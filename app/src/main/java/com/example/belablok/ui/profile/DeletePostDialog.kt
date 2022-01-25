package com.example.belablok.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.example.belablok.databinding.DialogDeletePostBinding
import com.example.belablok.extensions.onClick
import com.example.belablok.ui.viewmodels.DeletePostViewModel
import org.koin.android.ext.android.inject

class DeletePostDialog : DialogFragment() {
    private val viewmodel : DeletePostViewModel by inject()
    private lateinit var binding: DialogDeletePostBinding
    private val args: DeletePostDialogArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogDeletePostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            tvAccept.onClick {
                deletePost(args.id)
                dismiss()
            }
            tvCancel.onClick {
                dismiss()
            }
        }
    }
    override fun onStart() {
        super.onStart()
        isCancelable = false
    }
    private fun deletePost(id: Int) {
        viewmodel.deletePost(id)
    }

}