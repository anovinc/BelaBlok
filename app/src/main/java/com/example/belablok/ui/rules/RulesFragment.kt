package com.example.belablok.ui.rules

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.belablok.databinding.FragmentRulesBinding
import com.example.belablok.ui.base.BaseFragment

class RulesFragment : BaseFragment<FragmentRulesBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRulesBinding
        get() = FragmentRulesBinding::inflate

    override fun onPostViewCreated() {

    }


}