package com.example.mypolice.ui.dashboard.halo_polisi.pemadam

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mypolice.model.ModelTrackRecord
import com.example.mypolice.model.Result
import com.example.mypolice.utils.Repository
import com.google.android.gms.maps.model.LatLng

class PemadamViewModel(application: Application) : AndroidViewModel(application) {
//    val mapsData: LiveData<MutableList<Result>>
    private val repository= Repository()

    init {

    }
fun getData(context: Context,loc:LatLng,lokasi:String,name:String):LiveData<MutableList<Result>>{
    return repository.getMapsData(context,loc,lokasi,name)
}
}