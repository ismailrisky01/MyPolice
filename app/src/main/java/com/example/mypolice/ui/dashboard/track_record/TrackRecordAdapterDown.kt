package com.example.mypolice.ui.dashboard.track_record

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mypolice.R
import com.example.mypolice.databinding.ItemTrackUpBinding
import com.example.mypolice.model.ModelTrackRecord
import com.squareup.picasso.Picasso

class TrackRecordAdapterDown() : RecyclerView.Adapter<TrackRecordAdapterDown.ViewHolder>() {
    private var dataAdapter = emptyList<ModelTrackRecord>()

    fun setData(data: List<ModelTrackRecord>) {
        this.dataAdapter = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemTrackUpBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentData = dataAdapter[position]
        holder.binding.textView36.text = currentData.ketBerita
        val target = holder.binding.imageView32
        Picasso.get().load(currentData.imageBerita).into(target)
        holder.data(currentData)
    }


    override fun getItemCount() = dataAdapter.size

    class ViewHolder(val binding: ItemTrackUpBinding) : RecyclerView.ViewHolder(binding.root) {
        val data = Bundle()
        fun data(modelTrackRecords: ModelTrackRecord) {
            data.putString("imageKey",modelTrackRecords.imageBerita)
            data.putString("judulKey", modelTrackRecords.judulBerita)
            data.putString("keteranganKey", modelTrackRecords.ketBerita)
            data.putString("createdAtKey", modelTrackRecords.createdAt)
        }
        init {
            binding.CardView.setOnClickListener {
                it.findNavController().navigate(R.id.action_trackRecordFragment_to_detailTrackRecordFragment,data)
            }
        }
    }
}