package com.example.belablok.ui.authentication


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.belablok.R
import com.example.belablok.databinding.FragmentUserSignUpBinding
import com.example.belablok.ui.base.BaseFragment
import com.example.belablok.extensions.onClick
import com.example.belablok.ui.viewmodels.UserSignUpViewModel
import org.koin.android.ext.android.inject

class UserSignUpFragment : BaseFragment<FragmentUserSignUpBinding>() {
    private val viewmodel: UserSignUpViewModel by inject()
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentUserSignUpBinding
        get() = FragmentUserSignUpBinding::inflate

    private fun goToStart() {
        findNavController().navigate(R.id.action_userLoginFragment_to_drawerHostFragment)
    }

    private fun goToRegistration() {
        findNavController().navigate(R.id.action_userLoginFragment_to_userRegistrationFragment)
    }

    private fun initListeners() {
        binding.apply {
            tvSkipUserAuthencation.onClick {
                goToStart()
            }
            tvRegister.onClick {
                goToRegistration()
            }
            btnLogin.onClick {
                loginUser(getEmail(), getPassword())
            }
        }
    }

    override fun onPostViewCreated() {
        initListeners()
    }

    private fun isMailOrPasswordEmpty(email: String, password: String) =
        viewmodel.isMailOrPasswordEmpty(email, password)

    private fun getEmail() = binding.usernameInput.text.toString()
    private fun getPassword() = binding.passwordInput.text.toString()
    private fun loginUser(email: String, password: String) {
        if (isMailOrPasswordEmpty(email, password)) {
            viewmodel.loginUser(email, password) {
                if (it) {
                    goToStart()
                } else {
                    Toast.makeText(
                        context,
                        "Prijava nije uspijela, pokušajte ponovo",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
        else {
            Toast.makeText(context,"Ostavili ste prazna polja, sva polja moraju biti ispunjena",Toast.LENGTH_SHORT).show()
        }
    }

}