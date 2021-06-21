package com.example.mypolice.ui.dashboard.track_record

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mypolice.databinding.ItemTrackUpBinding
import com.example.mypolice.model.ModelTrackRecord
import com.squareup.picasso.Picasso

class TrackRecordAdapterDown() : RecyclerView.Adapter<TrackRecordAdapterDown.ViewHolder>() {
    private var dataAdapter = emptyList<ModelTrackRecord>()

    fun setData(data: List<ModelTrackRecord>) {
        this.dataAdapter = data
    }

    class ViewHolder(val binding: ItemTrackUpBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemTrackUpBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentData = dataAdapter[position]
        holder.binding.textView36.text = currentData.ketBerita
        val target = holder.binding.imageView32
        Picasso.get().load(currentData.imageBerita).into(target)
    }

    override fun getItemCount() = dataAdapter.size
}