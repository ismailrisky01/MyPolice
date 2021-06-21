package com.example.mypolice.ui.dashboard.e_tilang.cek_denda

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentCekDendaBinding
import com.example.mypolice.ui.chat.ChatViewModel
import com.example.mypolice.ui.dashboard.e_tilang.ETilangViewModel
import com.example.mypolice.utils.MyFragment


class CekDendaFragment : MyFragment<FragmentCekDendaBinding>(R.layout.fragment_cek_denda) {
    private lateinit var mCekDendaViewModel: ETilangViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mCekDendaViewModel = ViewModelProvider(this).get(ETilangViewModel::class.java)
        binding.IDCekDendaBtnCek.setOnClickListener {
            val nomorTilang = binding.IDCekDendaEdtNomor.text.toString()
val data = mCekDendaViewModel.cekTilang(nomorTilang)
            binding.editTextTextPersonName10.setText(data.nomorTilang)
            binding.editTextTextPersonName11.setText(data.biayaTilang.toString())
        }
    }
}