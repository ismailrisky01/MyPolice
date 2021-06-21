package com.example.mypolice.ui.dashboard.halo_polisi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mypolice.R
import com.example.mypolice.databinding.ItemChatBinding
import com.example.mypolice.databinding.ItemHaloPolisiBinding
import com.example.mypolice.model.ModelHaloPolisi
import com.example.mypolice.ui.chat.ChatAdapter

class HaloPolAdapter:RecyclerView.Adapter<HaloPolAdapter.ViewHolder>() {
    private var dataAdapter = emptyList<ModelHaloPolisi>()
    fun setData(data:List<ModelHaloPolisi>){
        this.dataAdapter = data
    }
    class ViewHolder(val binding: ItemHaloPolisiBinding):RecyclerView.ViewHolder(binding.root) {
init {
    binding.imageView15.setOnClickListener{
        it.findNavController().navigate(R.id.action_haloPolFragment_to_mapsFragment)
    }
}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHaloPolisiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentData = dataAdapter[position]
        holder.binding.imageView15.setImageResource(currentData.logoHalo)
        holder.binding.textView20.text = currentData.layananPolisi
    }

    override fun getItemCount(): Int {
       return dataAdapter.size
    }
}