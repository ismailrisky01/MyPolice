package com.example.mypolice.ui.dashboard.dokumen_kendaraan.sim

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentSimBlankoSatuBinding
import com.example.mypolice.utils.MyFragment

class SimBlankoSatuFragment :
    MyFragment<FragmentSimBlankoSatuBinding>(R.layout.fragment_sim_blanko_satu) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.IDSIMBlankoSatuBtnNext.setOnClickListener {
            findNavController().navigate(R.id.action_simBlankoSatuFragment_to_simBlankoDuaFragment)
        }
        binding.IDSIMBlankoSatuBtnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }


}