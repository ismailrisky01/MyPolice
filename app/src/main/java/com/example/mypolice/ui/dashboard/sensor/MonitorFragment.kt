package com.example.mypolice.ui.dashboard.sensor

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentDashboardBinding
import com.example.mypolice.databinding.FragmentMonitorBinding
import com.example.mypolice.model.ModelSensor
import com.example.mypolice.utils.MyFragment
import com.example.mypolice.utils.logD
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MonitorFragment : MyFragment<FragmentMonitorBinding>(R.layout.fragment_monitor){

private var mediaPlayer: MediaPlayer?=null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mediaPlayer = MediaPlayer.create(requireContext(),R.raw.beep)
        mediaPlayer?.setOnPreparedListener {
            logD("Ready to GO")
        }
        FirebaseDatabase.getInstance().reference.child("MyPolice/sensor/A1").addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val data = snapshot.getValue(ModelSensor::class.java) as ModelSensor
                logD(data.status)
                if (data.status){
                    Toast.makeText(requireContext(), "Warning", Toast.LENGTH_SHORT).show()
                    mediaPlayer?.start()
                    mediaPlayer?.isLooping = true
                }else{

                    mediaPlayer?.pause()
                    mediaPlayer?.seekTo(0)
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

}