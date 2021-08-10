package com.example.mypolice.ui.dashboard.halo_polisi.pemadam

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mypolice.R
import com.example.mypolice.databinding.ItemPemadamBinding
import com.example.mypolice.model.Geometry
import com.example.mypolice.model.Result

class PemadamAdapter : RecyclerView.Adapter<PemadamAdapter.ViewHolder>() {
    companion object {
        val keyName = "name"
        val keyLat = "lat"
        val keyLng = "lng"
        val keyCall = "call"
    }

    private var dataAdapter = emptyList<Result>()
    fun setData(data: List<Result>) {
        this.dataAdapter = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPemadamBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentData = dataAdapter[position]
        holder.binding.IDItemPemadamTxtNama.text = currentData.name
        holder.binding.IDItemPemadamTxtAlamat.text = currentData.vicinity
        holder.Data(currentData.geometry)
    }

    override fun getItemCount(): Int {
        return dataAdapter.size
    }

    class ViewHolder(val binding: ItemPemadamBinding) : RecyclerView.ViewHolder(binding.root) {
        var latitude: Double = 0.0
        var longitude: Double = 0.0

        init {
            binding.IDItemPemadamBtnNext.setOnClickListener {
                val bundle = Bundle()
                bundle.putBoolean(keyCall, true)
                bundle.putString("key", "")
                bundle.putString(keyName, "pemadam")
                bundle.putDouble(keyLat, latitude)
                bundle.putDouble(keyLng, longitude)
                    it.findNavController()
                        .navigate(R.id.action_pemadamListFragment_to_mapsFragment, bundle)
            }
        }

        fun Data(geometry: Geometry) {
            latitude = geometry.location.lat
            longitude = geometry.location.lng
        }
    }
}