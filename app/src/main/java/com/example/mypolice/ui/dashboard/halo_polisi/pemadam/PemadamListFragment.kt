package com.example.mypolice.ui.dashboard.halo_polisi.pemadam

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentPemadamListBinding
import com.example.mypolice.utils.MyFragment
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.OnSuccessListener

class PemadamListFragment : MyFragment<FragmentPemadamListBinding>(R.layout.fragment_pemadam_list) {
    private lateinit var mPemadamViewModel: PemadamViewModel
    private lateinit var mMap: GoogleMap

    companion object {
        private const val PERMISSION_REQUEST_ACCESS_FINE_LOCATION = 100
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPemadamViewModel = ViewModelProvider(this).get(PemadamViewModel::class.java)
        getLocation()
        binding.IDPemadamListBtnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    fun getLocation() {
        val mFusedLocation = LocationServices.getFusedLocationProviderClient(requireActivity())
        val adapter = PemadamAdapter()
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        mFusedLocation.lastLocation.addOnSuccessListener(
            requireActivity(),
            object : OnSuccessListener<Location> {
                override fun onSuccess(location: Location?) {
                    // Do it all with location
                    Log.d(
                        "My Current location",
                        "Lat : ${location?.latitude} Long : ${location?.longitude}"
                    )
                    // Display in Toast

                    mPemadamViewModel.getData(
                        requireContext(),
                        LatLng(location!!.latitude, location.longitude),
                        "Gas",
                        "Pemadam"
                    ).observe(viewLifecycleOwner,
                        {
                            binding.Recyclerview.layoutManager =
                                LinearLayoutManager(requireContext())
                            binding.Recyclerview.adapter = adapter
                            adapter.setData(it)
                        })
                }

            })
    }

}