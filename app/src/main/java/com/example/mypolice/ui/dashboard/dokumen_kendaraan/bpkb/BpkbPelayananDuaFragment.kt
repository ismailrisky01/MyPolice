package com.example.mypolice.ui.dashboard.dokumen_kendaraan.bpkb

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentBpkbPelayananDuaBinding
import com.example.mypolice.utils.MyFragment


class BpkbPelayananDuaFragment : MyFragment<FragmentBpkbPelayananDuaBinding>(R.layout.fragment_bpkb_pelayanan_dua) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.IDBPKBBtnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.IDBPKBBtnKembali.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}