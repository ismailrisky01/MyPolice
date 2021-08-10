package com.example.mypolice.ui.dashboard.dokumen_kendaraan.bpkb

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentBpkbProsedurBinding
import com.example.mypolice.databinding.FragmentSimBlankoTigaBinding
import com.example.mypolice.utils.MyFragment


class BpkbProsedurFragment : MyFragment<FragmentBpkbProsedurBinding>(R.layout.fragment_bpkb_prosedur) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.IDBPKBProsedurBtnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}