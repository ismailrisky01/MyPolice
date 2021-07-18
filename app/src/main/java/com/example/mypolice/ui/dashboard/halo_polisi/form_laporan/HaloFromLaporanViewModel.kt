package com.example.mypolice.ui.dashboard.halo_polisi.form_laporan

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mypolice.model.ModelChat
import com.example.mypolice.model.ModelLaporan
import com.example.mypolice.utils.Repository

class HaloFromLaporanViewModel (application: Application) : AndroidViewModel(application){
    private val repository= Repository()
    fun uploadLaporan( modelLaporan: ModelLaporan,context: Context ){
       repository.uploadLaporan(modelLaporan,context)
   }


}