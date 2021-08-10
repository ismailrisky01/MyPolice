package com.example.mypolice.ui.dashboard.skck.blanko

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.datetime.datePicker
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentBlankoSatuBinding
import com.example.mypolice.model.ModelBlankoSatu
import com.example.mypolice.utils.MyFragment
import com.example.mypolice.utils.SharedPref
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class BlankoSatuFragment : MyFragment<FragmentBlankoSatuBinding>(R.layout.fragment_blanko_satu) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        data()
        binding.IDBlankoSatuBtnLanjut.setOnClickListener {
            findNavController().navigate(R.id.action_blankoSatuFragment_to_blankoDuaFragment)
        }
        binding.IDBlankoSatuBtnSimpan.setOnClickListener {
            setDataBlanko()
        }
        binding.IDBlankoSatuBtnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.IDBlankoSatuBtnTanggalLahir.setOnClickListener {
            MaterialDialog(requireActivity()).show {
                datePicker { _, date ->
                    val myFormat = "MM/dd/yyyy" // mention the format you need
                    val sdf = SimpleDateFormat(myFormat, Locale.US)
                    val date = sdf.format(date.time)
                    binding.IDBlankoSatuTanggalLahir.setText("$date")
                }
            }
        }
    }


    fun data() {
        getDataBlanko()
        val locales: Array<Locale> = Locale.getAvailableLocales()
        val countries = ArrayList<String>()
        for (locale in locales) {
            val country: String = locale.displayCountry
            if (country.trim { it <= ' ' }.isNotEmpty() && !countries.contains(country)) {
                countries.add(country)
            }
        }

        val items = arrayOf("Islam", "Kristen", "Hindu")
        val adapterAgama = ArrayAdapter(requireContext(), R.layout.dropdown_item, items)
        val adapterKebangsaan = ArrayAdapter(requireContext(), R.layout.dropdown_item, countries)
        binding.IDBlankoSatuRgAgama.setAdapter(adapterAgama)
        binding.IDBlankoSatuEdtKebngsaan.setAdapter(adapterKebangsaan)
    }

    fun getDataBlanko() {
        val myPreference = SharedPref(requireContext())
        binding.IDBlankoSatuNamaLengkap.setText(myPreference.getDataBlankoSatu().namaLengkap)
        Log.d("BlankoSatu", "Data" + myPreference.getDataBlankoSatu().namaLengkap)

        binding.IDBlankoSatuTempatLahir.setText(myPreference.getDataBlankoSatu().tempatLahir)
        binding.IDBlankoSatuTanggalLahir.setText(myPreference.getDataBlankoSatu().tanggalLahir)
        binding.IDBlankoSatuRgAgama.setText(myPreference.getDataBlankoSatu().agama)
        binding.IDBlankoSatuEdtKebngsaan.setText(myPreference.getDataBlankoSatu().kebangsaan)
        if (myPreference.getDataBlankoSatu().jenisKelamin == "Laki-Laki") {
            binding.Laki.isChecked = true
        } else {
            binding.Laki.isChecked = false
            binding.Perempuan.isChecked = true
        }

        if (myPreference.getDataBlankoSatu().status == "Kawin") {
            binding.Kawin.isChecked = true
        } else {
            binding.Kawin.isChecked = false
            binding.Tidak.isChecked = true
        }

        //binding.IDBlankoSatuJenisKelamin.setText(myPreference.getDataBlankoSatu().namaLengkap)
        //binding.IDBlankoSatuStatus.setText(myPreference.getDataBlankoSatu().namaLengkap)
        binding.IDBlankoSatuPekerjaan.setText(myPreference.getDataBlankoSatu().pekerjaan)
        binding.IDBlankoSatuAlamat.setText(myPreference.getDataBlankoSatu().alamat)
        binding.IDBlankoSatuKTP.setText(myPreference.getDataBlankoSatu().noKtp)
        binding.IDBlankoSatuKK.setText(myPreference.getDataBlankoSatu().noKK)
        binding.IDBlankoSatuTelp.setText(myPreference.getDataBlankoSatu().noTelp)

    }

    fun setDataBlanko() {
        val nama = binding.IDBlankoSatuNamaLengkap.text.toString()
        val tempatLahir = binding.IDBlankoSatuTempatLahir.text.toString()
        val tanggalLahir = binding.IDBlankoSatuTanggalLahir.text.toString()
        val agama = binding.IDBlankoSatuRgAgama.text.toString()
        val kebangsaan = binding.IDBlankoSatuEdtKebngsaan.text.toString()
        val hasilJk: String
        resources.getResourceEntryName(binding.IDBlankoSatuJenisKelamin.checkedRadioButtonId)
            .apply {
                if (this == "Laki") {
                    hasilJk = "Laki-Laki"
                } else {
                    hasilJk = "Perempuan"
                }
            }

        val hasilStatus: String
        resources.getResourceEntryName(binding.IDBlankoSatuStatus.checkedRadioButtonId).apply {
            if (this == "Tidak") {
                hasilStatus = "Tidak Kawin"
            } else {
                hasilStatus = "Kawin"
            }
        }
        val pekerjaan = binding.IDBlankoSatuPekerjaan.text.toString()
        val alamat = binding.IDBlankoSatuAlamat.text.toString()
        val ktp = binding.IDBlankoSatuKTP.text.toString()
        val kk = binding.IDBlankoSatuKK.text.toString()
        val telp = binding.IDBlankoSatuTelp.text.toString()

        val data = ModelBlankoSatu(
            nama,
            tempatLahir,
            tanggalLahir,
            agama,
            kebangsaan,
            hasilJk,
            hasilStatus,
            pekerjaan,
            alamat,
            ktp,
            kk,
            telp
        )

        if (nama != "" && tempatLahir != "" && agama != "" && kebangsaan != "" && tanggalLahir != "" && hasilJk != "" && hasilStatus != "" && pekerjaan != "" && alamat != "" && ktp != "" && kk != "" && telp != "") {
            Log.d("BlankoSatu", data.namaLengkap + data.status + data.jenisKelamin)
            val myPreference = SharedPref(requireContext())
            myPreference.setDataBlankoSatu(data)

        } else {
            Log.d("BlankoSatu", "Isi dong")
            Toast.makeText(requireContext(), "Mohon Lengkapi Data", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onResume() {
        super.onResume()

    }
}