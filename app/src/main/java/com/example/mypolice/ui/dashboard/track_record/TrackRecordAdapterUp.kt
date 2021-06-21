package com.example.mypolice.ui.dashboard.track_record

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mypolice.databinding.ItemTrackUpBinding
import com.example.mypolice.model.ModelHaloPolisi
import com.example.mypolice.model.ModelTrackRecord
import com.squareup.picasso.Picasso

class TrackRecordAdapterUp() : RecyclerView.Adapter<TrackRecordAdapterUp.ViewHolder>() {
    private var dataAdapter = mutableListOf<ModelTrackRecord>()
val arrayList = arrayListOf("1","1")
    fun setData(data: MutableList<ModelTrackRecord>) {
        dataAdapter = data
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemTrackUpBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentData = dataAdapter[position]
        holder.binding.textView36.text = currentData.ketBerita
        Log.d("Track", currentData.imageBerita)
        val target = holder.binding.imageView32
        Picasso.get().load(currentData.imageBerita).into(target)
    }

    override fun getItemCount() = dataAdapter.size

    class ViewHolder(val binding: ItemTrackUpBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}