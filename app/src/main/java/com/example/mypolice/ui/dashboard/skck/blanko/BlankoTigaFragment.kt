package com.example.mypolice.ui.dashboard.skck.blanko

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentBlankoDuaBinding
import com.example.mypolice.databinding.FragmentBlankoTigaBinding
import com.example.mypolice.model.ModelBlankoTiga
import com.example.mypolice.utils.MyFragment
import com.example.mypolice.utils.SharedPref
import com.example.mypolice.utils.logD

class BlankoTigaFragment : MyFragment<FragmentBlankoTigaBinding>(R.layout.fragment_blanko_tiga) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataBlankoTiga()
        binding.IDBlankoTigaBtnSimpan.setOnClickListener {
            setDataBlankoTiga()
        }
        binding.IDBlankoTigaBtnBack.setOnClickListener {
            findNavController().popBackStack()

        }
        binding.IDBlankoTigaBtnLanjut.setOnClickListener {
            findNavController().navigate(R.id.action_blankoTigaFragment_to_blankoEmpatFragment)
        }
    }

    fun setDataBlankoTiga() {
        val namaSd = binding.IDBlankoTigaEdtNamaSd.text.toString()
        val kotaSd = binding.IDBlankoTigaEdtKotaSd.text.toString()
        val tahunSd = binding.IDBlankoTigaEdtTahunSd.text.toString()

        val namaSmp = binding.IDBlankoTigaEdtNamaSmp.text.toString()
        val kotaSMp = binding.IDBlankoTigaEdtKotaSmp.text.toString()
        val tahunSmp = binding.IDBlankoTigaEdtTahunSmp.text.toString()

        val namaSma = binding.IDBlankoTigaEdtNamaSma.text.toString()
        val kotaSma = binding.IDBlankoTigaEdtKotaSma.text.toString()
        val tahunSma = binding.IDBlankoTigaEdtTahunSma.text.toString()

        val namaUniv = binding.IDBlankoTigaEdtNamaUniv.text.toString()
        val kotaUniv = binding.IDBlankoTigaEdtKotaUniv.text.toString()
        val tahunUniv = binding.IDBlankoTigaEdtTahunUniv.text.toString()
        val data = ModelBlankoTiga(
            namaSd,
            kotaSd,
            tahunSd,
            namaSmp,
            kotaSMp,
            tahunSmp,
            namaSma,
            kotaSma,
            tahunSma,
            namaUniv,
            kotaUniv,
            tahunUniv
        )
        if (namaSd == "" && namaSmp == "" && namaSma == "" && namaUniv == "" && kotaSd == "" && kotaSMp == "" && kotaSma == "" && kotaUniv == "" && tahunSd == "" && tahunSmp == "" && tahunSma == "" && tahunUniv == "") {
            Toast.makeText(requireContext(), "Harap Di isi semua", Toast.LENGTH_SHORT).show()
        } else {
            logD(namaSd + "" + namaSmp)
            val myPreference = SharedPref(requireContext())
            myPreference.setDataBlankoTiga(data)
        }
    }

    fun getDataBlankoTiga() {
        val myPreference = SharedPref(requireContext())
        binding.IDBlankoTigaEdtNamaSd.setText(myPreference.getDataBlankoTiga().namaSD)
        binding.IDBlankoTigaEdtKotaSd.setText(myPreference.getDataBlankoTiga().kotaSD)
        binding.IDBlankoTigaEdtTahunSd.setText(myPreference.getDataBlankoTiga().tahunSD)

        binding.IDBlankoTigaEdtNamaSmp.setText(myPreference.getDataBlankoTiga().namaSMP)
        binding.IDBlankoTigaEdtKotaSmp.setText(myPreference.getDataBlankoTiga().kotaSMP)
        binding.IDBlankoTigaEdtTahunSmp.setText(myPreference.getDataBlankoTiga().tahunSMP)

        binding.IDBlankoTigaEdtNamaSma.setText(myPreference.getDataBlankoTiga().namaSMA)
        binding.IDBlankoTigaEdtKotaSma.setText(myPreference.getDataBlankoTiga().kotaSMA)
        binding.IDBlankoTigaEdtTahunSma.setText(myPreference.getDataBlankoTiga().tahunSMA)

        binding.IDBlankoTigaEdtNamaUniv.setText(myPreference.getDataBlankoTiga().namaUNIV)
        binding.IDBlankoTigaEdtKotaUniv.setText(myPreference.getDataBlankoTiga().kotaUNIV)
        binding.IDBlankoTigaEdtTahunUniv.setText(myPreference.getDataBlankoTiga().tahunUNIV)
    }

}
