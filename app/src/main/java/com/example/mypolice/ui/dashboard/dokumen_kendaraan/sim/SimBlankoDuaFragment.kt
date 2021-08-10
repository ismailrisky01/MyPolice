package com.example.mypolice.ui.dashboard.dokumen_kendaraan.sim

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentSimBlankoDuaBinding
import com.example.mypolice.databinding.FragmentSimBlankoSatuBinding
import com.example.mypolice.model.DataPermohonSim
import com.example.mypolice.utils.LoadingHelper
import com.example.mypolice.utils.MyFragment
import com.example.mypolice.utils.SharedPref
import es.dmoral.toasty.Toasty

class SimBlankoDuaFragment :
    MyFragment<FragmentSimBlankoDuaBinding>(R.layout.fragment_sim_blanko_dua) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        binding.IDDaftarPolisiBtnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.IDDaftarPolisiBtnBack2.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.IDBlankoDuaBtnNext.setOnClickListener {
            saveData()
        }
    }

    fun getData() {
        val mypref = SharedPref(requireContext()).getDataSimDataPemohon()
        if (mypref.jenisPermohonan == "SIM Baru") {
            binding.Baru.isChecked = true
            binding.Perpanjang.isChecked = false
        } else if (mypref.jenisPermohonan == "Perpanjang SIM") {
            binding.Baru.isChecked = false
            binding.Perpanjang.isChecked = true
        }

        if (mypref.golonganSim == "SIM A") {
            binding.SIMA.isChecked = true
            binding.SIMC.isChecked = false
        } else if (mypref.golonganSim == "SIM C") {
            binding.SIMA.isChecked = false
            binding.SIMC.isChecked = true
        }

        binding.IDSIMBlankoDuaEdtAlamatEmail.setText(mypref.alamatEmail)
        binding.IDSIMBlankoDuaEdtPoldaKedatangan.setText(mypref.poldaKedatangan)
        binding.IDSIMBlankoDuaEdtSatpasKedatangan.setText(mypref.satpasKedatangan)
        binding.IDSIMBlankoDuaEdtAlamatSatpasKedatangan.setText(mypref.alamatSatpas)
    }

    fun saveData() {
        val loading = LoadingHelper(requireContext())

        loading.show()
        val jenisPemohon: String
        val radioSIM =
            resources.getResourceEntryName(binding.IDSimBlankoDuaJenisPemohon.checkedRadioButtonId)
        if (radioSIM == "Baru") {
            jenisPemohon = "SIM Baru"
        } else {
            jenisPemohon = "Perpanjang SIM"
        }


        val golonganSIM: String
        val radioGolo = resources.getResourceEntryName(binding.IDSimBlankoDuaGolonganSIM.checkedRadioButtonId)
                if (radioGolo == "SIMA") {
                    golonganSIM = "SIM A"
                } else {
                    golonganSIM = "SIM C"
                }

        val alamatEmail = binding.IDSIMBlankoDuaEdtAlamatEmail.text.toString()
        val poldaKedatangan = binding.IDSIMBlankoDuaEdtPoldaKedatangan.text.toString()
        val satpasKedat = binding.IDSIMBlankoDuaEdtSatpasKedatangan.text.toString()
        val alamatSatpas = binding.IDSIMBlankoDuaEdtAlamatSatpasKedatangan.text.toString()

        if (jenisPemohon != "" && golonganSIM != "" && alamatEmail != "" && poldaKedatangan != "" && satpasKedat != "" && alamatSatpas != "") {
            val myshred = SharedPref(requireContext())
            myshred.setDataSimDataPemohon(
                DataPermohonSim(
                    jenisPemohon,
                    golonganSIM,
                    alamatEmail,
                    poldaKedatangan,
                    satpasKedat,
                    alamatSatpas
                )
            )
            loading.dismiss()
            Toasty.success(requireContext(), "Data Saved", Toasty.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_simBlankoDuaFragment_to_simBlankoTigaFragment)

        } else {
            loading.dismiss()
            Toasty.warning(requireContext(), "Please FIll all filed", Toasty.LENGTH_SHORT).show()
        }
    }

}