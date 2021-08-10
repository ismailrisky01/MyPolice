package com.example.mypolice.ui.dashboard.dokumen_kendaraan.stnk

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentStnkBlankoDuaBinding
import com.example.mypolice.databinding.FragmentStnkBlankoTigaBinding
import com.example.mypolice.utils.MyFragment


class stnkBlankoTigaFragment :
    MyFragment<FragmentStnkBlankoTigaBinding>(R.layout.fragment_stnk_blanko_tiga) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.IDSTNKBlankoTigaBtnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.IDSTNKBlankoTigaBtnBackBawah.setOnClickListener {
            findNavController().popBackStack()
        }
    }


}