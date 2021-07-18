package com.example.mypolice.ui.dashboard.daftar_polisi.informasi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mypolice.databinding.ItemInformasiBinding
import com.example.mypolice.model.ModelInformasi

class DaftarPolisiAdapter:RecyclerView.Adapter<DaftarPolisiAdapter.ViewHolder>() {
    private var dataAdapter = mutableListOf<ModelInformasi>()
    fun setData(data: MutableList<ModelInformasi>) {
        dataAdapter = data
    }
    class ViewHolder(val binding: ItemInformasiBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemInformasiBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentData = dataAdapter[position]
        val number = position+1
        holder.binding.IDItemLaporanTxtJudul.text = number.toString()+". "+currentData.judulInformasi
        holder.binding.IDItemLaporanTxtKonten.text = currentData.kontenInformasi

    }

    override fun getItemCount(): Int {
      return  dataAdapter.size
    }
}