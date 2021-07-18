package com.example.mypolice.ui.dashboard.skck.blanko

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentBlankoDuaBinding
import com.example.mypolice.model.ModelBlankoDua
import com.example.mypolice.utils.MyFragment
import com.example.mypolice.utils.SharedPref
import com.example.mypolice.utils.logD


class BlankoDuaFragment : MyFragment<FragmentBlankoDuaBinding>(R.layout.fragment_blanko_dua) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        binding.IDBlankoDuaBtnLanjut.setOnClickListener {
            findNavController().navigate(R.id.action_blankoDuaFragment_to_blankoTigaFragment)
        }
        binding.IDBlankoDuaBtnSimpan.setOnClickListener {
            setData()
        }
    }


    fun getData() {
        val myPreference = SharedPref(requireContext())
        binding.IDBlankoDuaEdtNamaAyah.setText(myPreference.getDataBlankoDua().namaBapak)
        binding.IDBlankoDuaEdtTempatLahirAyah.setText(myPreference.getDataBlankoDua().tempatLahirAyah)
        binding.IDBlankoDuaEdtTanggalLahirAyah.setText(myPreference.getDataBlankoDua().tanggalLahirAyah)
        binding.IDBlankoDuaEdtAgamaAyah.setText(myPreference.getDataBlankoDua().agamaBapak)
        binding.IDBlankoDuaEdtKebangsaanAyah.setText(myPreference.getDataBlankoDua().kebangsaanBapak)
        //binding.IDBlankoDuaEdtStatusAyah.setText(myPreference.getDataBlankoDua().statusBapak)
        binding.IDBlankoDuaEdtPekerjaanAyah.setText(myPreference.getDataBlankoDua().pekerjaanBapak)
        binding.IDBlankoDuaEdtAlamatAyah.setText(myPreference.getDataBlankoDua().alamatBapak)
        binding.IDBlankoDuaEdtNamaIbu.setText(myPreference.getDataBlankoDua().namaIbu)
        binding.IDBlankoDuaEdtTempatLahirIbu.setText(myPreference.getDataBlankoDua().tempatLahirIbu)
        binding.IDBlankoDuaEdtTanggalLahirIbu.setText(myPreference.getDataBlankoDua().tanggalLahirIbu)
        binding.IDBlankoDuaEdtAgamaIbu.setText(myPreference.getDataBlankoDua().agamaIbu)
        binding.IDBlankoDuaEdtKebangsaanIbu.setText(myPreference.getDataBlankoDua().kebangsaanIbu)
        //binding.IDBlankoDuaEdtStatusIbu.setText(myPreference.getDataBlankoDua().statusIbu)
        binding.IDBlankoDuaEdtPekerjaanIbu.setText(myPreference.getDataBlankoDua().pekerjaanIbu)
        binding.IDBlankoDuaEdtAlamatIbu.setText(myPreference.getDataBlankoDua().alamatIbu)


    }

    fun setData() {
        val namaAyah = binding.IDBlankoDuaEdtNamaAyah.text.toString()
        val tempatLahirAyah = binding.IDBlankoDuaEdtTempatLahirAyah.text.toString()
        val tanggalLahirAyah = binding.IDBlankoDuaEdtTanggalLahirAyah.text.toString()
        val agamaAyah = binding.IDBlankoDuaEdtAgamaAyah.text.toString()
        val kebangsaanAyah = binding.IDBlankoDuaEdtKebangsaanAyah.text.toString()
        val statusAyah: String
        resources.getResourceEntryName(binding.IDBlankoDuaEdtStatusAyah.checkedRadioButtonId)
            .apply {
                if (this == "KawinAyah") {
                    statusAyah = "Kawin"
                } else {
                    statusAyah = "Tidak Kawin"
                }
            }
        val pekerjaanAyah = binding.IDBlankoDuaEdtPekerjaanAyah.text.toString()
        val alamatAyah = binding.IDBlankoDuaEdtAlamatAyah.text.toString()

        val namaIbu = binding.IDBlankoDuaEdtNamaIbu.text.toString()
        val tempatLahirIbu = binding.IDBlankoDuaEdtTempatLahirIbu.text.toString()
        val tanggalLahirIbu = binding.IDBlankoDuaEdtTanggalLahirIbu.text.toString()
        val agamaIbu = binding.IDBlankoDuaEdtAgamaIbu.text.toString()
        val kebangsaanIbu = binding.IDBlankoDuaEdtKebangsaanIbu.text.toString()
        val statusIbu: String
        resources.getResourceEntryName(binding.IDBlankoDuaEdtStatusIbu.checkedRadioButtonId)
            .apply {
                if (this == "KawinIbu") {
                    statusIbu = "Kawin"
                } else {
                    statusIbu = "Tidak Kawin"
                }
            }
        val pekerjaanIbu = binding.IDBlankoDuaEdtPekerjaanIbu.text.toString()
        val alamatIbu = binding.IDBlankoDuaEdtAlamatIbu.text.toString()
        val data = ModelBlankoDua(
            namaAyah,
            tempatLahirAyah,
            tanggalLahirAyah,
            agamaAyah,
            kebangsaanAyah,
            statusAyah,
            pekerjaanAyah,
            alamatAyah,
            namaIbu,
            tempatLahirIbu,
            tanggalLahirIbu,
            agamaIbu,
            kebangsaanIbu,
            statusIbu,
            pekerjaanIbu,
            alamatIbu
        )
        if (namaAyah == "" && tempatLahirAyah == " " && tanggalLahirAyah == "" && agamaAyah == "" && kebangsaanAyah == ""  && pekerjaanAyah == "" && alamatAyah == ""
            && namaIbu == "" && tempatLahirIbu == "" && tanggalLahirIbu == "" && agamaIbu == "" && kebangsaanIbu == "" && pekerjaanIbu == "" && alamatIbu == ""
        ) {
            logD("Isi Dong")
            Toast.makeText(requireContext(), "Mohon Lengkapi Data", Toast.LENGTH_SHORT).show()
        } else {
            logD(namaAyah + tempatLahirAyah)
            val myPreference = SharedPref(requireContext())
            myPreference.setDataBlankoDua(data)
        }
    }
}