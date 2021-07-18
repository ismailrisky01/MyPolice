package com.example.mypolice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mypolice.databinding.FragmentDashboardBinding
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