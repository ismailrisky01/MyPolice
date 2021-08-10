package com.example.mypolice.ui.dashboard.dokumen_kendaraan.sim

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentSimBlankoDuaBinding
import com.example.mypolice.databinding.FragmentSimBlankoTigaBinding
import com.example.mypolice.model.DataDiriSim
import com.example.mypolice.utils.MyFragment
import com.example.mypolice.utils.SharedPref
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import es.dmoral.toasty.Toasty

class SimBlankoTigaFragment :
    MyFragment<FragmentSimBlankoTigaBinding>(R.layout.fragment_sim_blanko_tiga) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        binding.IDSIMBlankoTigaBtnDaftar.setOnClickListener {
            konfirmasi()
        }
        binding.IDSimBlankoTigaBtnBack.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    private fun konfirmasi() {
        val noKtp = binding.IDBlankoTigaEdtKTP.text.toString()
        val namaLengkap = binding.IDBlankoTigaEdtNamaLengkap.text.toString()
        val goldar = binding.IDBlankoTigaEdtGolonganDarahValue.text.toString()
        val kodePos = binding.IDBlankoTigaEdtKodePos.text.toString()
        val kota = binding.IDBlankoTigaEdtKota.text.toString()
        val alamat = binding.IDBlankoTigaEdtAlamat.text.toString()
        val noHp = binding.IDBlankoTigaEdtNoHp.text.toString()
        val pendidikan = binding.IDBlankoTigaEdtPendidikan.text.toString()
        val pekerjaan = binding.IDBlankoTigaEdtPekerjaan.text.toString()

        if (noKtp != "" && namaLengkap != "" && goldar != "" && kodePos != "" && kota != "" && alamat != "" && noHp != "" && pendidikan != "" && pekerjaan != "") {
            MaterialAlertDialogBuilder(requireContext()).setCancelable(false)
                .setMessage("Apakah data yang anda isikan sudah sesuai?")
                .setNegativeButton("Belum") { dialog, which ->

                }
                .setPositiveButton("Sudah") { dialog, which ->
                    saveData()
                    Toasty.success(requireContext(), "Data Saved", Toasty.LENGTH_SHORT).show()
                }.show()
        } else {
            Toasty.warning(requireContext(), "Please Fill all field", Toasty.LENGTH_SHORT).show()
        }
    }

    fun saveData() {
        val kewarganegaraan: String
        val radioKWN =
            resources.getResourceEntryName(binding.IDBlankoTigaKewarganegaraan.checkedRadioButtonId)
        if (radioKWN == "WNI") {
            kewarganegaraan = "WNI"
        } else {
            kewarganegaraan = "WNA"
        }
        val nik = binding.IDBlankoTigaEdtKTP.text.toString()
        val namaLengkap = binding.IDBlankoTigaEdtNamaLengkap.text.toString()
        val golonganDarah = binding.IDBlankoTigaEdtGolonganDarahValue.text.toString()
        val kodePos = binding.IDBlankoTigaEdtKodePos.text.toString()
        val kota = binding.IDBlankoTigaEdtKota.text.toString()
        val alamat = binding.IDBlankoTigaEdtAlamat.text.toString()
        val noHp = binding.IDBlankoTigaEdtNoHp.text.toString()
        val pendidikan = binding.IDBlankoTigaEdtPendidikan.text.toString()
        val pekerjaan = binding.IDBlankoTigaEdtPekerjaan.text.toString()

        val mypref = SharedPref(requireContext())

        mypref.setDataPribadi(
            DataDiriSim(
                kewarganegaraan,
                nik,
                namaLengkap,
                golonganDarah,
                kodePos,
                kota,
                alamat,
                noHp,
                pendidikan,
                pekerjaan
            )
        )
    }

    fun getData() {
        val mypref = SharedPref(requireContext()).getDataPribadi()
        if (mypref.kewarganegaraan == "WNI") {
            binding.WNI.isChecked = true
            binding.WNA.isChecked = false
        } else if (mypref.kewarganegaraan == "WNA") {
            binding.WNI.isChecked = false
            binding.WNA.isChecked = true
        }

        binding.IDBlankoTigaEdtKTP.setText(mypref.nik)
        binding.IDBlankoTigaEdtNamaLengkap.setText(mypref.namaLengkap)
        binding.IDBlankoTigaEdtGolonganDarahValue.setText(mypref.golonganDarah)

        val items = arrayOf("A", "B", "O","AB")
        val adapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, items)
        binding.IDBlankoTigaEdtGolonganDarahValue.setAdapter(adapter)
//        val adapter = ArrayAdapter.createFromResource(
//            requireContext(),
//            R.array.golonganDarah, android.R.layout.simple_spinner_item
//        )
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        binding.IDBlankoTigaEdtGolonganDarah.adapter = adapter
//        when (mypref.golonganDarah) {
//            "A" -> binding.IDBlankoTigaEdtGolonganDarah.setSelection(0)
//            "B" -> binding.IDBlankoTigaEdtGolonganDarah.setSelection(1)
//            "O" -> binding.IDBlankoTigaEdtGolonganDarah.setSelection(2)
//            "AB" -> binding.IDBlankoTigaEdtGolonganDarah.setSelection(3)
//        }

        binding.IDBlankoTigaEdtKodePos.setText(mypref.kodePos)
        binding.IDBlankoTigaEdtKota.setText(mypref.kota)
        binding.IDBlankoTigaEdtAlamat.setText(mypref.alamat)
        binding.IDBlankoTigaEdtNoHp.setText(mypref.noHandphone)
        binding.IDBlankoTigaEdtPendidikan.setText(mypref.pendidikan)
        binding.IDBlankoTigaEdtPekerjaan.setText(mypref.pekerjaan)

    }

}