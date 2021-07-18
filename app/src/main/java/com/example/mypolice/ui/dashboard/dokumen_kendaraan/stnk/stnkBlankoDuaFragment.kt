package com.example.mypolice.ui.dashboard.dokumen_kendaraan.stnk

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentStnkBlankoDuaBinding
import com.example.mypolice.utils.MyFragment


class stnkBlankoDuaFragment : MyFragment<FragmentStnkBlankoDuaBinding>(R.layout.fragment_stnk_blanko_dua) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.IDStnkBlankoDuaBtnLanjut.setOnClickListener {
            findNavController().navigate(R.id.action_stnkBlankoDuaFragment_to_stnkBlankoTigaFragment)
        }
    }
}