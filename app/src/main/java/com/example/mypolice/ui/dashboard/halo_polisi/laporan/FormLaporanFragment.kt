package com.example.mypolice.ui.dashboard.halo_polisi.laporan

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentFormLaporanBinding
import com.example.mypolice.model.ModelLaporanKejadian
import com.example.mypolice.ui.profile.ProfileViewModel
import com.example.mypolice.utils.LoadingHelper
import com.example.mypolice.utils.MyFragment
import com.example.mypolice.utils.SharedPref
import com.google.firebase.firestore.FirebaseFirestore
import es.dmoral.toasty.Toasty

class FormLaporanFragment : MyFragment<FragmentFormLaporanBinding>(R.layout.fragment_form_laporan) {
    private var data: String? = null
    private lateinit var mLaporanViewmodel: LaporanViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mLaporanViewmodel = ViewModelProvider(this).get(LaporanViewModel::class.java)

        arguments?.let {bundle->
            data=bundle.getString("data")
        }
        binding.IDFormLaporanBtnKonfirmasi.setOnClickListener {
            if (binding.IDFormLaporanCheckBox.isChecked.equals(true)){
                postData(data!!)
            }
            else{
                Toast.makeText(requireContext(), "Please centang", Toast.LENGTH_SHORT).show()
            }
        }
        binding.IDFormLaporanBtnBack.setOnClickListener {
            findNavController().navigate(R.id.action_formLaporanFragment_to_menuLaporanFragment)
        }
    }
fun postData(kategori:String){
    val loading = LoadingHelper(requireContext())
    loading.show()
    val alamatKejadian = binding.IDFormLaporanEdtAlamat.text.toString()
    val tangalKejadian = binding.IDFormLaporanEdtTanggal.text.toString()
    val deskripsiKejadian = binding.IDFormLaporanEdtDeskripsi.text.toString()
    val myprefrence = SharedPref(requireContext())
    val identitasPelapor = myprefrence.getDataUser().noKTP
    val data = ModelLaporanKejadian("",identitasPelapor,kategori,alamatKejadian,tangalKejadian,deskripsiKejadian)
    mLaporanViewmodel.uploadLaporan(data,requireContext(),kategori).apply {
        clear()
    }
}
    fun clear(){
        binding.IDFormLaporanEdtAlamat.setText("")
        binding.IDFormLaporanEdtTanggal.setText("")
        binding.IDFormLaporanEdtDeskripsi.setText("")
        binding.IDFormLaporanCheckBox.isChecked = false
    }
}