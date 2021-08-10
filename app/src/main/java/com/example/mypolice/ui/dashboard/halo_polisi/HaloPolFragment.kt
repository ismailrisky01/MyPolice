package com.example.mypolice.ui.dashboard.halo_polisi

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentHaloPolBinding
import com.example.mypolice.ui.profile.ProfileViewModel
import com.example.mypolice.utils.LoadingHelper
import com.example.mypolice.utils.MyFragment


class HaloPolFragment : MyFragment<FragmentHaloPolBinding>(R.layout.fragment_halo_pol){
    private lateinit var mProfileViewmodel: ProfileViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mProfileViewmodel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        binding.Card1.setOnClickListener {
            checkProfile()
        }
        binding.IDHaloBtnBack.setOnClickListener {

       findNavController().popBackStack()
        }
        binding.Card3.setOnClickListener {

            val bundle=Bundle()
            bundle.putString("key","police")
            bundle.putString("name","polsek")
            findNavController().navigate(R.id.action_haloPolFragment_to_mapsFragment,bundle)
        }
        binding.Card4.setOnClickListener {
            val bundle=Bundle()
            bundle.putString("key","hospital")
            bundle.putString("name","UGD")

            findNavController().navigate(R.id.action_haloPolFragment_to_mapsFragment,bundle)
        }
        binding.Card5.setOnClickListener {
            val bundle=Bundle()
            bundle.putString("key","gas station")
            bundle.putString("name","pom")
            findNavController().navigate(R.id.action_haloPolFragment_to_mapsFragment,bundle)
        }
        binding.Card6.setOnClickListener {
            findNavController().navigate(R.id.action_haloPolFragment_to_pemadamListFragment)
        }
        binding.Card7.setOnClickListener {
            findNavController().navigate(R.id.action_haloPolFragment_to_covidFormLaporanFragment)
        }

    }


    fun checkProfile(){
        mProfileViewmodel.getDataProfile(requireContext()).observe(viewLifecycleOwner, Observer {
            if (it.noKTP == "" && it.noKTP.isEmpty()) {
                findNavController().navigate(R.id.action_haloPolFragment_to_profileFragment)
                Toast.makeText(
                    requireContext(),
                    "Mohon Lengkapi Profile Anda !",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                findNavController().navigate(R.id.action_haloPolFragment_to_menuLaporanFragment)
            }
        })
    }


}