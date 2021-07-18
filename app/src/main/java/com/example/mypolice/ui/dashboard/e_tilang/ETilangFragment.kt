package com.example.mypolice.ui.dashboard.e_tilang

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentChatBinding
import com.example.mypolice.databinding.FragmentETilangBinding
import com.example.mypolice.utils.MyFragment


class ETilangFragment : MyFragment<FragmentETilangBinding>(R.layout.fragment_e_tilang) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.IDETilangBtnCekDenda.setOnClickListener {
            findNavController().navigate(R.id.action_ETilangFragment_to_cekDendaFragment)
        }
        binding.IDETilangBtnETLE.setOnClickListener {
            findNavController().navigate(R.id.action_ETilangFragment_to_mekanismeEtleFragment)
        }
        binding.IDETilangBtnCaraBayar.setOnClickListener {
            findNavController().navigate(R.id.action_ETilangFragment_to_brivaFragment)
        }
    }


}