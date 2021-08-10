package com.example.mypolice.ui.dashboard.dokumen_kendaraan.bpkb

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentBpkbBinding
import com.example.mypolice.utils.MyFragment


class BpkbFragment : MyFragment<FragmentBpkbBinding>(R.layout.fragment_bpkb) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.IDBPKBBtnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.IDBPKBTxtRegister.setOnClickListener {
            findNavController().navigate(R.id.action_bpkbFragment_to_bpkbRegistrasiFragment)
        }

        binding.IDBPKBTxtProsedur.setOnClickListener {
            findNavController().navigate(R.id.action_bpkbFragment_to_bpkbProsedurFragment)
        }
        binding.IDBPKBDaftarBaru.setOnClickListener {
            findNavController().navigate(R.id.action_bpkbFragment_to_bpkbPelayananSaturagment2)
        }
        binding.IDBPKBStatusPendaftaran.setOnClickListener {
            findNavController().navigate(R.id.action_bpkbFragment_to_bpkbPelayananDuaFragment)
        }
        binding.IDBPKBKecocokan.setOnClickListener {
            findNavController().navigate(R.id.action_bpkbFragment_to_bpkbPelayananTigaFragment)
        }
    }
}