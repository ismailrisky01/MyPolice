package com.example.mypolice.ui.dashboard.dokumen_kendaraan.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mypolice.model.IdentitasKendaraan
import com.example.mypolice.model.ModelStnk
import com.example.mypolice.model.ModelUser
import com.example.mypolice.model.RincianKendaraan
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class RepositoryDokumen {
    private val uid = FirebaseAuth.getInstance().currentUser?.uid
    private val firebaserealtime = FirebaseDatabase.getInstance().reference.child("MyPolice")
    private val firestore = FirebaseFirestore.getInstance()

    fun getIdentitas(): LiveData<ModelStnk> {
        val mutableList = MutableLiveData<ModelStnk>()
        val data = ModelStnk(
            IdentitasKendaraan(
                "AK 2143 THS",
                "SEPEDA MOTOR",
                "HONDA",
                "A5 WGYIUU JP",
                "2018",
                "HITAM PUTIH",
                "23-11-2021",
                "23-11-2023"
            ),
            RincianKendaraan(279000, 0, 0, 35000, 0, 0, 12500, 326500)
        )
        mutableList.value = data
        return mutableList

    }
}