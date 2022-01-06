package com.example.belablok.ui.authentication

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.belablok.R
import com.example.belablok.databinding.FragmentUserRegistrationBinding
import com.example.belablok.ui.base.BaseFragment
import com.example.belablok.extensions.onClick
import com.example.belablok.ui.viewmodels.UserRegistrationViewModel
import org.koin.android.ext.android.inject

class UserRegistrationFragment : BaseFragment<FragmentUserRegistrationBinding>() {
    private val viewmodel: UserRegistrationViewModel by inject()
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentUserRegistrationBinding
        get() = FragmentUserRegistrationBinding::inflate

    private fun initListeners() {
        binding.apply {
            btnLogin.onClick {
                registerUser(getEmail(), getPassword())
            }
        }
    }

    private fun goToLogin() {
        findNavController().navigate(R.id.action_userRegistrationFragment_to_userLoginFragment)
    }

    override fun onPostViewCreated() {
        initListeners()
    }

    private fun isMailOrPasswordEmpty(email: String, password: String) =
        viewmodel.isMailOrPasswordEmpty(email, password)

    private fun registerUser(email: String, password: String) {
        if (isMailOrPasswordEmpty(email, password)) {
            viewmodel.registerNewUser(email, password) {
                if (it) {
                    goToLogin()
                } else {
                    Toast.makeText(
                        context,
                        "Registracija nije uspijela, provjerite duljinu lozinke(MIN. 6 znakova) ili ispravnost email adrese.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        } else {
            Toast.makeText(
                context,
                "Ostavili ste prazna polja, sva polja moraju biti ispunjena",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun getEmail() = binding.usernameInput.text.toString()
    private fun getPassword() = binding.passwordInput.text.toString()

}
