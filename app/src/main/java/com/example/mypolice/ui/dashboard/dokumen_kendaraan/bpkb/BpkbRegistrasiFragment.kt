package com.example.mypolice.ui.dashboard.dokumen_kendaraan.bpkb

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentBpkbRegistrasiBinding
import com.example.mypolice.databinding.FragmentSimBlankoTigaBinding
import com.example.mypolice.utils.MyFragment


class BpkbRegistrasiFragment : MyFragment<FragmentBpkbRegistrasiBinding>(R.layout.fragment_bpkb_registrasi) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.IDBPKBRegistrasiBtnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}