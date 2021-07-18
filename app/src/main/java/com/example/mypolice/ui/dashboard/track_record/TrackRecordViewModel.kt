package com.example.mypolice.ui.dashboard.track_record

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mypolice.model.ModelChat
import com.example.mypolice.model.ModelTrackRecord
import com.example.mypolice.utils.Repository

class TrackRecordViewModel(application: Application) : AndroidViewModel(application) {
    val trackData: LiveData<MutableList<ModelTrackRecord>>
    private val repository= Repository()

    init {
        trackData = repository.readTrackData()

    }


}