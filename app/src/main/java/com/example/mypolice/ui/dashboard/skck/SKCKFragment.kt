package com.example.mypolice.ui.dashboard.skck

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentDashboardBinding
import com.example.mypolice.databinding.FragmentSKCKBinding
import com.example.mypolice.utils.MyFragment


class SKCKFragment : MyFragment<FragmentSKCKBinding>(R.layout.fragment_s_k_c_k) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.IDSKSCBtnIsiBlanko.setOnClickListener {
            findNavController().navigate(R.id.action_SKCKFragment_to_blankoSatuFragment)
        }
    }

}