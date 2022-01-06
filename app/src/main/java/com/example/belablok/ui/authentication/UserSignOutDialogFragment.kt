package com.example.belablok.ui.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.findNavController
import com.example.belablok.R
import com.example.belablok.databinding.DialogFragmentUserSignOutBinding
import com.example.belablok.extensions.onClick
import com.example.belablok.ui.viewmodels.UserSignOutViewModel
import org.koin.android.ext.android.inject

class UserSignOutDialogFragment : DialogFragment() {
    private lateinit var binding: DialogFragmentUserSignOutBinding
    private val viewmodel : UserSignOutViewModel by inject()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogFragmentUserSignOutBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initListeners()
    }

    private fun initListeners() {
        with(binding){
            tvCancel.onClick {
                dismiss()
            }
            tvAccept.onClick {
                viewmodel.signOutUser()
                dismiss()
                goToLogin()
            }
        }
    }
    override fun onStart() {
        super.onStart()
        isCancelable = false
    }
    private fun goToLogin() {
        val navController = activity?.findNavController(R.id.nav_host_fragment)
        navController?.navigate(R.id.action_userSignOutDialogFragment_to_userLoginFragment)
    }
}