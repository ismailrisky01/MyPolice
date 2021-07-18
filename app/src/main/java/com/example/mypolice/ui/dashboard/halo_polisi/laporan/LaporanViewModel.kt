package com.example.mypolice.ui.dashboard.halo_polisi.laporan

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mypolice.model.ModelLaporan
import com.example.mypolice.model.ModelLaporanKejadian
import com.example.mypolice.utils.Repository

class LaporanViewModel(application: Application) : AndroidViewModel(application) {
    private val repository= Repository()
    fun uploadLaporan(modelLaporanKejadian: ModelLaporanKejadian, context: Context,kategori:String){
       repository.uploadLaporanKejadian(modelLaporanKejadian,context,kategori)
    }
}