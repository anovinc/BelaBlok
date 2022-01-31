package com.example.belablok.ui.about

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.belablok.databinding.FragmentAboutBinding
import com.example.belablok.ui.base.BaseFragment

class AboutFragment : BaseFragment<FragmentAboutBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAboutBinding
        get() = FragmentAboutBinding::inflate

    override fun onPostViewCreated() {

    }


}