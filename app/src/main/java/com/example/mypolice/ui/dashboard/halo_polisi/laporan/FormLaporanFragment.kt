package com.example.mypolice.ui.dashboard.halo_polisi.laporan

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.datetime.datePicker
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentFormLaporanBinding
import com.example.mypolice.model.ModelLaporanKejadian
import com.example.mypolice.ui.profile.ProfileViewModel
import com.example.mypolice.utils.*
import com.google.firebase.firestore.FirebaseFirestore
import es.dmoral.toasty.Toasty
import java.text.SimpleDateFormat
import java.util.*

class FormLaporanFragment : MyFragment<FragmentFormLaporanBinding>(R.layout.fragment_form_laporan) {
    private var data: String? = null
    private lateinit var mLaporanViewmodel: LaporanViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mLaporanViewmodel = ViewModelProvider(this).get(LaporanViewModel::class.java)

        arguments?.let { bundle ->
            data = bundle.getString("data")
        }
        binding.IDFormLaporanBtnKonfirmasi.setOnClickListener {
            if (binding.IDFormLaporanCheckBox.isChecked.equals(true)) {
                val alamat = binding.IDFormLaporanEdtAlamat.text.toString()
                val tangal = binding.IDFormLaporanEdtTanggal.text.toString()
                val deskripsi = binding.IDFormLaporanEdtDeskripsi.text.toString()
                if (alamat.isEmpty() && tangal.isEmpty() && deskripsi.isEmpty()) {
                    Toasty.warning(requireContext(), "Please fill all field", Toasty.LENGTH_LONG) .show()
                } else {
                    postData(data!!)

                }
            } else {
                Toasty.warning(requireContext(), "Please Check Syarat dan Ketentuan", Toasty.LENGTH_LONG).show()

            }
        }
        binding.IDFormLaporanBtnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.IDFormLaporanBtnDate.setOnClickListener {
//            val tanggal  = DatePickerMe().getDatePicker(requireContext()).toString()

            MaterialDialog(requireActivity()).show {
                datePicker { _, date ->
                    val myFormat = "MM/dd/yyyy" // mention the format you need
                    val sdf = SimpleDateFormat(myFormat, Locale.US)
                    val date = sdf.format(date.time)
                    binding.IDFormLaporanEdtTanggal.setText("$date")
                }
            }
        }
        binding.IDFormLaporanKebijakanPrivasi.setOnClickListener {
            val dialog = Dialog(requireContext())
            dialog.show()
        }
    }

    fun postData(kategori: String) {

        val alamatKejadian = binding.IDFormLaporanEdtAlamat.text.toString()
        val tangalKejadian = binding.IDFormLaporanEdtTanggal.text.toString()
        val deskripsiKejadian = binding.IDFormLaporanEdtDeskripsi.text.toString()
        val myprefrence = SharedPref(requireContext())
        val identitasPelapor = myprefrence.getDataUser().noKTP
        val data = ModelLaporanKejadian(
            "",
            identitasPelapor,
            kategori,
            alamatKejadian,
            tangalKejadian,
            deskripsiKejadian
        )
        mLaporanViewmodel.uploadLaporan(data, requireContext(), kategori).apply {
            clear()
        }
    }

    fun clear() {
        binding.IDFormLaporanEdtAlamat.setText("")
        binding.IDFormLaporanEdtTanggal.setText("")
        binding.IDFormLaporanEdtDeskripsi.setText("")
        binding.IDFormLaporanCheckBox.isChecked = false
    }
}