package com.example.mypolice.ui.dashboard.e_tilang

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentTentangEtleBinding
import com.example.mypolice.utils.MyFragment


class TentangEtleFragment : MyFragment<FragmentTentangEtleBinding>(R.layout.fragment_tentang_etle) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.IDTentangEtleBtnMekanisme.setOnClickListener {
            findNavController().navigate(R.id.action_tentangEtleFragment_to_mekanismeEtleFragment)
        }
        binding.IDTentangEtleBtnBriva.setOnClickListener {
            findNavController().navigate(R.id.action_tentangEtleFragment_to_brivaFragment)
        }

    }
}