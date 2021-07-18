package com.example.mypolice.ui.dashboard.dokumen_kendaraan.stnk

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentStnkBlankoSatuBinding
import com.example.mypolice.utils.MyFragment


class StnkBlankoSatuFragment : MyFragment<FragmentStnkBlankoSatuBinding>(R.layout.fragment_stnk_blanko_satu) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.IDStnkBlankoSatuBtnCekData.setOnClickListener { findNavController().navigate(R.id.action_stnkBlankoSatuFragment_to_stnkBlankoDuaFragment) }
    }
}