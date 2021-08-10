package com.example.mypolice.ui.dashboard.sensor

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mypolice.model.ModelSensor
import com.example.mypolice.model.ModelSensorKu
import com.example.mypolice.model.ModelUser
import com.example.mypolice.utils.LoadingHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore

class RepoSensor {

    fun getSensor(context: Context, namaAlat: String): LiveData<ModelSensor> {
        val loading = LoadingHelper(context)
        loading.show()
        val mutableList = MutableLiveData<ModelSensor>()
        FirebaseDatabase.getInstance().reference.child("MyPolice/sensor")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val datas = snapshot.children.mapNotNull {
                        it.getValue(ModelSensor::class.java) as ModelSensor
                    }.filter {
                        it.namaAlat == namaAlat
                    }
                    datas.forEach {
                        mutableList.value = it
                    }
                    loading.dismiss()
                }

                override fun onCancelled(error: DatabaseError) {
                    loading.dismiss()
                }
            })

        return mutableList
    }
}