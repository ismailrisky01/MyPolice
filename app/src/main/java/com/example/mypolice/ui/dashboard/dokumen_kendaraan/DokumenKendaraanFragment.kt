package com.example.mypolice.ui.dashboard.dokumen_kendaraan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentDashboardBinding
import com.example.mypolice.databinding.FragmentDokumenKendaraanBinding
import com.example.mypolice.utils.MyFragment


class DokumenKendaraanFragment : MyFragment<FragmentDokumenKendaraanBinding>(R.layout.fragment_dokumen_kendaraan) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.simCardView.setOnClickListener {
            findNavController().navigate(R.id.action_dokumenKendaraanFragment_to_simBlankoSatuFragment)
        }
        binding.stnkCardView.setOnClickListener {
            findNavController().navigate(R.id.action_dokumenKendaraanFragment_to_stnkBlankoSatuFragment)
        }
        binding.bpkbCardView.setOnClickListener {
            findNavController().navigate(R.id.action_dokumenKendaraanFragment_to_bpkbFragment)
        }
    }
}