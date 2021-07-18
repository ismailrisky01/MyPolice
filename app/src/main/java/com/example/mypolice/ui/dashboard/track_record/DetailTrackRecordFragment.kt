package com.example.mypolice.ui.dashboard.track_record

import android.os.Bundle
import android.view.View
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentDetailTrackRecordBinding
import com.example.mypolice.utils.MyFragment
import com.squareup.picasso.Picasso

class DetailTrackRecordFragment : MyFragment<FragmentDetailTrackRecordBinding>(R.layout.fragment_detail_track_record) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val judul = it.getString("judulKey")
            val keterangan = it.getString("keteranganKey")
            val image = it.getString("imageKey")
            val createdAt = it.getString("createdAtKey")

            binding.IDDetailTrackTxtJudul.text = judul
            Picasso.get().load(image).into(binding.IDDetailTrackImage)
            binding.IDDetailTrackTxtKeterangan.text = keterangan



        }
    }

}