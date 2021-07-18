package com.example.mypolice.ui.dashboard.halo_polisi.laporan

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentMenuLaporanBinding
import com.example.mypolice.ui.profile.ProfileViewModel
import com.example.mypolice.utils.LoadingHelper
import com.example.mypolice.utils.MyFragment


class MenuLaporanFragment : MyFragment<FragmentMenuLaporanBinding>(R.layout.fragment_menu_laporan) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = Bundle()
        binding.IDMenuLaporanCard1.setOnClickListener {
            data.putString("data","kekerasan perempuan dan anak")
            findNavController().navigate(R.id.action_menuLaporanFragment_to_formLaporanFragment,data)
        }
        binding.IDMenuLaporanCard2.setOnClickListener {
            data.putString("data","kebakaran")
            findNavController().navigate(R.id.action_menuLaporanFragment_to_formLaporanFragment,data)
        }
        binding.IDMenuLaporanCard3.setOnClickListener {
            data.putString("data","kriminal")
            findNavController().navigate(R.id.action_menuLaporanFragment_to_formLaporanFragment,data)
        }
        binding.IDMenuLaporanCard4.setOnClickListener {
            data.putString("data","kedaruratan medis")
            findNavController().navigate(R.id.action_menuLaporanFragment_to_formLaporanFragment,data)
        }
        binding.IDMenuLaporanCard5.setOnClickListener {
            data.putString("data","sos")
            findNavController().navigate(R.id.action_menuLaporanFragment_to_formLaporanFragment,data)
        }

        binding.IDMenuLaporanCard6.setOnClickListener {
            data.putString("data","ketentraman umum")
            findNavController().navigate(R.id.action_menuLaporanFragment_to_formLaporanFragment,data)
        }

        binding.IDMenuLaporanCard7.setOnClickListener {
            data.putString("data","kecelakaan")
            findNavController().navigate(R.id.action_menuLaporanFragment_to_formLaporanFragment,data)
        }

        binding.IDMenuLaporanCard8.setOnClickListener {
            data.putString("data","lalu lintas")
            findNavController().navigate(R.id.action_menuLaporanFragment_to_formLaporanFragment,data)
        }

        binding.IDMenuLaporanBtnBack.setOnClickListener {
            findNavController().popBackStack()
        }


    }


}