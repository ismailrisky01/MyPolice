package com.example.mypolice.ui.dashboard.halo_polisi

import android.Manifest
import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.mypolice.R
import com.example.mypolice.model.ModelChat
import com.example.mypolice.model.ModelRoot
import com.example.mypolice.model.Result
import com.example.mypolice.utils.logD
import com.example.mypolice.utils.retrofit.Network
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapsFragment : Fragment(),OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var myMarker: Marker? = null
    private var latitude=0.0
    private var longitude=0.0
    private lateinit var mutableList: MutableLiveData<List<Result>>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        GlobalScope.async {


        }

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


        //--
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        mMap.isMyLocationEnabled = true
        fusedLocationClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())
        fusedLocationClient.lastLocation.addOnSuccessListener { loc ->
            latitude = loc.latitude
            longitude = loc.longitude
            val sydney = LatLng(loc.latitude, loc.longitude)
//            googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
            mMap.animateCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(
                        loc.latitude,
                        loc.longitude
                    ), 15.0f
                )
            )

            mutableList = MutableLiveData<List<Result>>()
            val lokasi = arguments?.getString("key").toString()
            Network().getServiceData().getData(loc.latitude.toString()+","+loc.longitude.toString(),"2000",lokasi,"AIzaSyAt6rC_iziBuu0k3tscBmPp--H3kC7qwas")
                .enqueue(object : Callback<ModelRoot> {
                    override fun onResponse(call: Call<ModelRoot>, response: Response<ModelRoot>) {
                        logD("Retrofit")

                        logD(response.body()?.results)
                        mutableList.value = response.body()!!.results

                        mutableList.observe(viewLifecycleOwner, Observer {
                            it.forEach {
                                logD(it.name)
                                val latitude = it.geometry.location.lat
                                val longitude = it.geometry.location.lng
                                myMarker = mMap.addMarker(MarkerOptions().position(LatLng(latitude,longitude)).title(it.name))
                                myMarker?.showInfoWindow()
                            }
                        })

                    }

                    override fun onFailure(call: Call<ModelRoot>, t: Throwable) {
                        logD(t.message+t.localizedMessage)
                    }
                })
        }
//--



    }
}