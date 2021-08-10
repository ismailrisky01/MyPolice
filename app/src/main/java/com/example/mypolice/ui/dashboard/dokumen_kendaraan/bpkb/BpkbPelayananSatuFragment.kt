package com.example.mypolice.ui.dashboard.dokumen_kendaraan.bpkb

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentBpkbPelayananSatuBinding
import com.example.mypolice.utils.MyFragment

class BpkbPelayananSaturagment : MyFragment<FragmentBpkbPelayananSatuBinding>(R.layout.fragment_bpkb_pelayanan_satu) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.IDBPKBPelayananBtnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}