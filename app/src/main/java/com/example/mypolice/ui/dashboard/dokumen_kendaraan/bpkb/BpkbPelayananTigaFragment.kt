package com.example.mypolice.ui.dashboard.dokumen_kendaraan.bpkb

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentBpkbPelayananTigaBinding
import com.example.mypolice.utils.MyFragment


class BpkbPelayananTigaFragment : MyFragment<FragmentBpkbPelayananTigaBinding>(R.layout.fragment_bpkb_pelayanan_tiga) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.IDBPKBBtnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}