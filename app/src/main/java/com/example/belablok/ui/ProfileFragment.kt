package com.example.belablok.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.example.belablok.PrefsManager
import com.example.belablok.databinding.FragmentProfileBinding
import com.example.belablok.extensions.onClick
import com.example.belablok.ui.base.BaseFragment

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentProfileBinding
        get() = FragmentProfileBinding::inflate
    val launchActivity = registerForActivityResult(ActivityResultContracts.GetContent()) {


    }

    override fun onPostViewCreated() {
        val user = PrefsManager().getUser()?.replaceFirstChar { it.titlecase() }
        binding.tvUsername.text = user
        binding.btnNewPost.onClick {
            launchActivity.launch("image/*")
        }
    }


}