package com.example.mypolice.ui.dashboard.track_record

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentHaloPolBinding
import com.example.mypolice.databinding.FragmentTrackRecordBinding
import com.example.mypolice.databinding.ItemTrackUpBinding
import com.example.mypolice.model.ModelTrackRecord
import com.example.mypolice.ui.chat.ChatViewModel
import com.example.mypolice.utils.MyFragment
import kotlin.math.log


class TrackRecordFragment : MyFragment<FragmentTrackRecordBinding>(R.layout.fragment_track_record) {
    private lateinit var mTrackViewModel: TrackRecordViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapterUp = TrackRecordAdapterUp()
        val adapterDown = TrackRecordAdapterDown()
        mTrackViewModel = ViewModelProvider(this).get(TrackRecordViewModel::class.java)

        binding.IDTrackRecyclerviewUp.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.IDTrackRecyclerviewUp.adapter =adapterUp
        binding.IDTrackRecyclerviewDown.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.IDTrackRecyclerviewDown.adapter =adapterDown
        mTrackViewModel.getData().observe(viewLifecycleOwner, Observer {
            Log.d("TrackFrag",it.toString())
            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
            adapterUp.setData(it)
            adapterUp.notifyDataSetChanged()
            adapterDown.setData(it)
            adapterDown.notifyDataSetChanged()
        })

    }

}