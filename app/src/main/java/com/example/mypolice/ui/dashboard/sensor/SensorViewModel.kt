package com.example.mypolice.ui.dashboard.sensor

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mypolice.model.ModelSensor
import com.example.mypolice.model.ModelSensorKu
import com.example.mypolice.model.ModelTrackRecord
import com.example.mypolice.utils.Repository

class SensorViewModel(application: Application) : AndroidViewModel(application) {


    private val repository= RepoSensor()

    fun getSensor(context: Context,namaAlat:String):LiveData<ModelSensor>{
        return repository.getSensor(context,namaAlat)


    }
}