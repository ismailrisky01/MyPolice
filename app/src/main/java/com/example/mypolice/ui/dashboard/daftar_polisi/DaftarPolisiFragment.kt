package com.example.mypolice.ui.dashboard.daftar_polisi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentDaftarPolisiBinding
import com.example.mypolice.databinding.FragmentDashboardBinding
import com.example.mypolice.utils.MyFragment


class DaftarPolisiFragment: MyFragment<FragmentDaftarPolisiBinding>(R.layout.fragment_daftar_polisi) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.IDDaftarPolisiBtnInformasi.setOnClickListener {
            findNavController().navigate(R.id.action_daftarPolisiFragment2_to_daftarPolisiInformasiDanLinkFragment)
        }
        binding.IDDaftarPolisiBtnTugas.setOnClickListener {
            findNavController().navigate(R.id.action_daftarPolisiFragment2_to_daftarPolisiTugasDanFungsiFragment)
        }
    }

}