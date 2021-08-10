package com.example.mypolice.ui.dashboard.e_tilang

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentMekanismeEtleBinding
import com.example.mypolice.utils.MyFragment


class MekanismeEtleFragment : MyFragment<FragmentMekanismeEtleBinding>(R.layout.fragment_mekanisme_etle) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.IDMekanismeEtleBtnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }




}