package com.example.mypolice.utils

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mypolice.R
import com.example.mypolice.model.ModelChat
import com.example.mypolice.model.ModelTilang
import com.example.mypolice.model.ModelTrackRecord
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class Repository {
    val uid = FirebaseAuth.getInstance().currentUser?.uid
    val firebaserealtime = FirebaseDatabase.getInstance().reference.child("MyPolice")
val firestore = FirebaseFirestore.getInstance()
    init {

    }
    fun readChatData(): LiveData<List<ModelChat>> {
        val ref = firebaserealtime.child("datachatpolisis")
        val mutableList = MutableLiveData<List<ModelChat>>()
        ref.addValueEventListener(object : ValueEventListener{
            val data = mutableListOf<ModelChat>()
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach{
                    val dataSnapshot = it.getValue(ModelChat::class.java)as ModelChat
                    data.add(dataSnapshot)
                }
                mutableList.value = data
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        return mutableList
    }

    fun readTrackData(): LiveData<MutableList<ModelTrackRecord>> {

firestore.collection("TrackRecord").document().set(ModelTrackRecord("wqw","qwq"))
        val mutableList = MutableLiveData<MutableList<ModelTrackRecord>>()
        firebaserealtime.child("trackrecord").limitToFirst(2).addValueEventListener(object: ValueEventListener{
            val data = mutableListOf<ModelTrackRecord>()
            override fun onDataChange(snapshot: DataSnapshot) {
                data.clear()
                snapshot.children.forEach {
                    val dataSnapshot = it.getValue(ModelTrackRecord::class.java)as ModelTrackRecord
                    val gmbr = dataSnapshot.imageBerita
                    val bertia = dataSnapshot.ketBerita
                    Log.d("Repo",bertia.toString())
                    data.add(ModelTrackRecord(gmbr,bertia))
                }

                mutableList.value = data
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        return mutableList
    }

    fun getInfoTilang(nomorTilang: String):ModelTilang {

        val data = ModelTilang(nomorTilang, 20000)

        return data
    }

}