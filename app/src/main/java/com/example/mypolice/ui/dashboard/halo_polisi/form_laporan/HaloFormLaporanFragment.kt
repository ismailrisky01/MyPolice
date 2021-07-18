package com.example.mypolice.ui.dashboard.halo_polisi.form_laporan

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentHaloFormLaporanBinding
import com.example.mypolice.model.ModelLaporan
import com.example.mypolice.ui.profile.ProfileViewModel
import com.example.mypolice.utils.DatePickerMe
import com.example.mypolice.utils.LoadingHelper
import com.example.mypolice.utils.MyFragment
import com.example.mypolice.utils.SharedPref
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*


class HaloFormLaporanFragment :
    MyFragment<FragmentHaloFormLaporanBinding>(R.layout.fragment_halo_form_laporan) {
    private lateinit var mProfileViewmodel: ProfileViewModel
    private lateinit var mHaloFormLaporanViewmodel: HaloFromLaporanViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mProfileViewmodel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        mHaloFormLaporanViewmodel = ViewModelProvider(this).get(HaloFromLaporanViewModel::class.java)

        cekDataProfile()

        binding.IDHaloBtnSimpan.setOnClickListener {
            uploadDataLaporan()
        }
        binding.IDHaloBtnBack.setOnClickListener {
            findNavController().navigate(R.id.action_haloFormLaporanFragment_to_haloPolFragment)
        }

    }

    fun cekDataProfile() {
        val loading = LoadingHelper(requireContext())

        loading.show()
        mProfileViewmodel.getDataProfile(requireContext()).observe(viewLifecycleOwner, Observer {
            if (it.noKTP == "" && it.noKTP.isEmpty()) {
                loading.dismiss()

                Toast.makeText(
                    requireContext(),
                    "Mohon Lengkapi Profile Anda !",
                    Toast.LENGTH_SHORT
                ).show()
                findNavController().navigate(R.id.action_haloFormLaporanFragment_to_profileFragment)
            } else {
                loading.dismiss()
                binding.IDHaloEdtIdentitas.setText(it.noKTP)
            }
        })
        binding.IDHaloEdtWaktu.setOnClickListener{
           val date = DatePickerMe().getDatePicker(requireContext())
            binding.IDHaloEdtWaktu.setText(date)

        }


    }


    fun uploadDataLaporan() {
        loading.show()
        val identitas = binding.IDHaloEdtIdentitas.text.toString()
        val waktu = binding.IDHaloEdtWaktu.text.toString()
        val tempat_kejadian = binding.IDHaloEdtTempat.text.toString()
        val yangterjadi = binding.IDHaloEdtYangTerjadi.text.toString()
        val terlapor = binding.IDHaloEdtTerlapor.text.toString()
        val korban = binding.IDHaloEdtKorban.text.toString()
        val bagaimana_terjadi = binding.IDHaloEdtBagaimanaTerjadi.text.toString()
        val saksi = binding.IDHaloEdtSaksi.text.toString()
        val identitas_saksi1 = binding.IDHaloEdtIdentitasSaksi1.text.toString()
        val identitas_saksi2 = binding.IDHaloEdtIdentitasSaksi2.text.toString()
        val uraiansingkatkejadian = binding.IDHaloEdtUraianSingkat.text.toString()
        val mypreference = SharedPref(requireContext())
        val sdf = SimpleDateFormat("dd-M-yyyy")
        val time = sdf.format(Date())
        mHaloFormLaporanViewmodel.uploadLaporan(ModelLaporan(
          "",
            identitas,
            time,
            mypreference.getDataUser().noKTP,
            waktu,
            tempat_kejadian,
            yangterjadi,
            terlapor,
            korban,
            bagaimana_terjadi,
            saksi,
            identitas_saksi1,
            identitas_saksi2,
            uraiansingkatkejadian
        ),requireContext())

    }
}