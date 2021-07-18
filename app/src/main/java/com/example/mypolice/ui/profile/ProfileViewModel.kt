package com.example.mypolice.ui.profile

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mypolice.model.ModelUser
import com.example.mypolice.utils.Repository

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
//    val profileData:LiveData<ModelUser>
    private val repository= Repository()
    init {

    }
    fun getDataProfile(context: Context):LiveData<ModelUser>{
        return repository.getProfileData(context)
    }
    fun updateData(modelUser: ModelUser,context: Context){
        repository.updateProfile(modelUser,context)
    }
}