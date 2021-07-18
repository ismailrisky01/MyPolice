package com.example.mypolice.ui.dashboard.halo_polisi.bencana

import android.app.Application
import android.content.Context
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mypolice.model.ModelBencana
import com.example.mypolice.model.ModelChat
import com.example.mypolice.model.ModelLaporan
import com.example.mypolice.utils.Repository

class HaloBencanaViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = Repository()

    fun uploadImageBencana(uri: Uri): LiveData<String> {
        return repository.postImageBencana(uri)
    }

    fun uploadDataBencana(modelBencana: ModelBencana, context: Context) {
        repository.postDataBenca(modelBencana, context)
    }
}