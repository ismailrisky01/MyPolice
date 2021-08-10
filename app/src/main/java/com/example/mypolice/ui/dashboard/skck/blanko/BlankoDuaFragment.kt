package com.example.mypolice.ui.dashboard.skck.blanko

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.datetime.datePicker
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentBlankoDuaBinding
import com.example.mypolice.model.ModelBlankoDua
import com.example.mypolice.model.ModelSaudara
import com.example.mypolice.utils.LoadingHelper
import com.example.mypolice.utils.MyFragment
import com.example.mypolice.utils.SharedPref
import com.example.mypolice.utils.logD
import es.dmoral.toasty.Toasty
import java.text.SimpleDateFormat
import java.util.*


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
        binding.IDBlankoDuaBtnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.IDBlankoDuaEdtBtnTanggalLahirAyah.setOnClickListener {
            MaterialDialog(requireActivity()).show {
                datePicker { _, date ->
                    val myFormat = "MM/dd/yyyy" // mention the format you need
                    val sdf = SimpleDateFormat(myFormat, Locale.US)
                    val date = sdf.format(date.time)
                    binding.IDBlankoDuaEdtTanggalLahirAyah.setText("$date")
                }
            }
        }

        binding.IDBlankoDuaEdtBtnTanggalLahirIbu.setOnClickListener {
            MaterialDialog(requireActivity()).show {
                datePicker { _, date ->
                    val myFormat = "MM/dd/yyyy" // mention the format you need
                    val sdf = SimpleDateFormat(myFormat, Locale.US)
                    val date = sdf.format(date.time)
                    binding.IDBlankoDuaEdtTanggalLahirIbu.setText("$date")
                }
            }
        }


    }


    fun getData() {
        val myPreference = SharedPref(requireContext())
        binding.IDBlankoDuaEdtNamaAyah.setText(myPreference.getDataBlankoDua().namaBapak)
        binding.IDBlankoDuaEdtTempatLahirAyah.setText(myPreference.getDataBlankoDua().tempatLahirAyah)
        binding.IDBlankoDuaEdtTanggalLahirAyah.setText(myPreference.getDataBlankoDua().tanggalLahirAyah)
        binding.IDBlankoDuaEdtAgamaAyah.setText(myPreference.getDataBlankoDua().agamaBapak)
        binding.IDBlankoDuaEdtKebangsaanAyah.setText(myPreference.getDataBlankoDua().kebangsaanBapak)
        val statusBapak = myPreference.getDataBlankoDua().statusBapak
        if (statusBapak=="Kawin"){
            binding.KawinAyah.isChecked = true
            binding.TidakAyah.isChecked = false
        }else{
            binding.KawinAyah.isChecked = false
            binding.TidakAyah.isChecked = true
        }
        binding.IDBlankoDuaEdtPekerjaanAyah.setText(myPreference.getDataBlankoDua().pekerjaanBapak)
        binding.IDBlankoDuaEdtAlamatAyah.setText(myPreference.getDataBlankoDua().alamatBapak)
        binding.IDBlankoDuaEdtNamaIbu.setText(myPreference.getDataBlankoDua().namaIbu)
        binding.IDBlankoDuaEdtTempatLahirIbu.setText(myPreference.getDataBlankoDua().tempatLahirIbu)
        binding.IDBlankoDuaEdtTanggalLahirIbu.setText(myPreference.getDataBlankoDua().tanggalLahirIbu)
        binding.IDBlankoDuaEdtAgamaIbu.setText(myPreference.getDataBlankoDua().agamaIbu)
        binding.IDBlankoDuaEdtKebangsaanIbu.setText(myPreference.getDataBlankoDua().kebangsaanIbu)
        val statusIbu = myPreference.getDataBlankoDua().statusIbu
        if (statusIbu=="Kawin"){
            binding.KawinIbu.isChecked = true
            binding.TidakIbu.isChecked = false
        }else{
            binding.KawinIbu.isChecked = false
            binding.TidakIbu.isChecked = true
        }
        //binding.IDBlankoDuaEdtStatusIbu.setText(myPreference.getDataBlankoDua().statusIbu)
        binding.IDBlankoDuaEdtPekerjaanIbu.setText(myPreference.getDataBlankoDua().pekerjaanIbu)
        binding.IDBlankoDuaEdtAlamatIbu.setText(myPreference.getDataBlankoDua().alamatIbu)
        val dataSaudara = myPreference.getDataBlankoDua().dataSaudara
        dataSaudara.forEach {
            binding.IDBlankoDuaEdtNamaSaudara.setText(it.namaSaudara)
            binding.IDBlankoDuaEdtTempatLahirSaudara.setText(it.tempatLahirSaudara)
            binding.IDBlankoDuaEdtPekerjaanSaudara.setText(it.pekerjaanSaudara)
            binding.IDBlankoDuaEdtAlamatSaudara.setText(it.alamatSaudara)

        }


    }

    fun setData() {
        val loading = LoadingHelper(requireContext())
        loading.show()
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


        val dataSaudara = ArrayList<ModelSaudara>()
        dataSaudara.add(ModelSaudara("Salsa","Ngawi","31/01/2013","Sekolah","Paron"))
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
            alamatIbu,
            dataSaudara
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
            Toasty.success(requireContext(),"Saved",Toasty.LENGTH_SHORT).show()
        }
        loading.dismiss()
    }
}