package com.example.mypolice.ui.dashboard.halo_polisi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentChatBinding
import com.example.mypolice.databinding.FragmentHaloPolBinding
import com.example.mypolice.model.ModelHaloPolisi
import com.example.mypolice.utils.MyFragment


class HaloPolFragment : MyFragment<FragmentHaloPolBinding>(R.layout.fragment_halo_pol) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
binding.Card1.setOnClickListener { findNavController().navigate(R.id.action_haloPolFragment_to_haloFormLaporanFragment) }

binding.IDHaloBtnBack.setOnClickListener {
    findNavController().navigate(R.id.action_haloPolFragment_to_dashboardFragment)
}
    }


}