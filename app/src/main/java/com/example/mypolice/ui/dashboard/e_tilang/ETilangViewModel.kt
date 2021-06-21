package com.example.mypolice.ui.dashboard.e_tilang

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypolice.model.ModelTilang
import com.example.mypolice.utils.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ETilangViewModel(application: Application): AndroidViewModel(application) {
    private val repository= Repository()

    fun cekTilang(nomorTilang:String):ModelTilang{
     return repository.getInfoTilang(nomorTilang)
    }
}