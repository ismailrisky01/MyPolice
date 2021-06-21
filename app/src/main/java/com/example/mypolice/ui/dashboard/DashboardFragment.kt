package com.example.mypolice.ui.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentDashboardBinding
import com.example.mypolice.utils.MyFragment
import com.google.firebase.auth.FirebaseAuth


class DashboardFragment : MyFragment<FragmentDashboardBinding>(R.layout.fragment_dashboard),
    View.OnClickListener {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = FirebaseAuth.getInstance().currentUser
        if (user == null) {
            findNavController().navigate(R.id.action_dashboardFragment_to_loginFragment)
        }
        binding.IDDashboardCardView1.setOnClickListener(this)
        binding.IDDashboardCardView2.setOnClickListener(this)
        binding.IDDashboardCardView3.setOnClickListener(this)
        binding.IDDashboardCardView4.setOnClickListener(this)
        binding.IDDashboardCardView5.setOnClickListener(this)
        binding.IDDashboardCardView6.setOnClickListener(this)
        binding.IDDashboardCardView7.setOnClickListener(this)
        binding.IDDashboardWelcome.text = "Welcome " + user?.email


    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ID_Dashboard_CardView1 -> findNavController().navigate(R.id.action_dashboardFragment_to_haloPolFragment)
            R.id.ID_Dashboard_CardView2 -> findNavController().navigate(R.id.action_dashboardFragment_to_trackRecordFragment)
            R.id.ID_Dashboard_CardView3 -> findNavController().navigate(R.id.action_dashboardFragment_to_dokumenKendaraanFragment)
            R.id.ID_Dashboard_CardView4 -> findNavController().navigate(R.id.action_dashboardFragment_to_ETilangFragment)
            R.id.ID_Dashboard_CardView5 -> findNavController().navigate(R.id.action_dashboardFragment_to_SKCKFragment)
            R.id.ID_Dashboard_CardView6 -> findNavController().navigate(R.id.action_dashboardFragment_to_daftarPolisiFragment2)

        }
    }


}