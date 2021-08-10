package com.example.mypolice.ui.dashboard.daftar_polisi.tugas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentDaftarPolisiTugasDanFungsiBinding
import com.example.mypolice.utils.MyFragment


class DaftarPolisiTugasDanFungsiFragment : MyFragment<FragmentDaftarPolisiTugasDanFungsiBinding>(R.layout.fragment_daftar_polisi_tugas_dan_fungsi) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.IDDaftarTugasDanFungsiPolBtnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}