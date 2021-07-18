package com.example.mypolice.ui.dashboard.halo_polisi

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentHaloPolBinding
import com.example.mypolice.ui.profile.ProfileViewModel
import com.example.mypolice.utils.LoadingHelper
import com.example.mypolice.utils.MyFragment


class HaloPolFragment : MyFragment<FragmentHaloPolBinding>(R.layout.fragment_halo_pol) {
    private lateinit var mProfileViewmodel: ProfileViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mProfileViewmodel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        binding.Card1.setOnClickListener {
            checkProdile()
        }
        binding.IDHaloBtnBack.setOnClickListener {

            findNavController().navigate(R.id.action_haloPolFragment_to_dashboardFragment)
        }
        binding.Card2.setOnClickListener {

            findNavController().navigate(R.id.action_haloPolFragment_to_menuLaporanFragment)
        }
        binding.Card3.setOnClickListener {
            val bundle=Bundle()
            bundle.putString("key","police")
            findNavController().navigate(R.id.action_haloPolFragment_to_mapsFragment,bundle)
        }
        binding.Card4.setOnClickListener {
            val bundle=Bundle()
            bundle.putString("key","hospital")
            findNavController().navigate(R.id.action_haloPolFragment_to_mapsFragment,bundle)
        }
        binding.Card5.setOnClickListener {
            val bundle=Bundle()
            bundle.putString("key","gas station")
            findNavController().navigate(R.id.action_haloPolFragment_to_mapsFragment,bundle)
        }
        binding.Card6.setOnClickListener {
            findNavController().navigate(R.id.action_haloPolFragment_to_mapsFragment)
        }
        binding.Card7.setOnClickListener {
            findNavController().navigate(R.id.action_haloPolFragment_to_mapsFragment)
        }

    }
    fun checkProdile(){
        val loading = LoadingHelper(requireContext())

        loading.show()
        mProfileViewmodel.getDataProfile(requireContext()).observe(viewLifecycleOwner, Observer {
            if (it.noKTP == "" && it.noKTP.isEmpty()) {
                loading.dismiss()
                findNavController().navigate(R.id.action_haloPolFragment_to_profileFragment)

                Toast.makeText(
                    requireContext(),
                    "Mohon Lengkapi Profile Anda !",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                findNavController().navigate(R.id.action_haloPolFragment_to_menuLaporanFragment)
                loading.dismiss()
            }
        })
    }


}