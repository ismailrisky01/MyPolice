package com.example.mypolice.ui.dashboard.halo_polisi

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentMapsBinding
import com.example.mypolice.model.ModelRoot
import com.example.mypolice.model.Result
import com.example.mypolice.ui.dashboard.halo_polisi.pemadam.PemadamAdapter
import com.example.mypolice.utils.MyFragment
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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MapsFragment : MyFragment<FragmentMapsBinding>(R.layout.fragment_maps),OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var myMarker: Marker? = null
    private var latitude=0.0
    private var longitude=0.0
    private lateinit var mutableList: MutableLiveData<List<Result>>


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
        binding.IDMapsBtnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.IDMapsCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "113"))
            startActivity(intent)
        }
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
                    ), 18.0f
                )
            )
            if (requireArguments().getBoolean(PemadamAdapter.keyCall)){
                binding.IDMapsConstrain.visibility = View.VISIBLE
                val lat = arguments?.getDouble(PemadamAdapter.keyLat) as Double
                val lng = arguments?.getDouble(PemadamAdapter.keyLng)  as Double
                binding.IDMapsConstrain.visibility = View.VISIBLE
                myMarker = mMap.addMarker(MarkerOptions().position(LatLng(lat,lng)))

            }else{
                mutableList = MutableLiveData<List<Result>>()
                val lokasi = arguments?.getString("key").toString()
                val name = arguments?.getString(PemadamAdapter.keyName).toString()
//                Toast.makeText(requireContext(), ""+name, Toast.LENGTH_SHORT).show()
                Network().getServiceData().getData(loc.latitude.toString()+","+loc.longitude.toString(),"2000",lokasi,name,"AIzaSyAt6rC_iziBuu0k3tscBmPp--H3kC7qwas")
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

        }
//--



    }

    override fun onDestroy() {
        super.onDestroy()

    }

}