package com.example.mypolice.ui.dashboard.skck

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentKodePendaftaranBinding
import com.example.mypolice.utils.MyFragment
import com.example.mypolice.utils.SharedPref
import com.google.firebase.auth.FirebaseAuth
import net.glxn.qrgen.android.QRCode

//import net.glxn.qrgen.android.QRCode


class KodePendaftaranFragment : MyFragment<FragmentKodePendaftaranBinding>(R.layout.fragment_kode_pendaftaran) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val text = FirebaseAuth.getInstance().currentUser?.uid
binding.IDKonfirmasiBtnBack.setOnClickListener {
    findNavController().popBackStack()
}
        setData()
        binding.IDKodePenBtnAmbilAntrian.setOnClickListener {getBarcode(text)  }
        binding.IDKodePenBtnEdit.setOnClickListener { findNavController().navigate(R.id.action_kodePendaftaranFragment_to_blankoSatuFragment) }
    }
    fun getBarcode(text:String?){
        binding.IDKodePenConstrain.visibility = View.GONE
        val bitmap = QRCode.from(text).withSize(1000, 1000).bitmap()
        binding.IDKodePenImage.setImageBitmap(bitmap)
        binding.IDKodePenImage.visibility = View.VISIBLE
    }
    fun setData(){
        val data = SharedPref(requireContext())
        binding.IDKodePenImage.visibility = View.GONE
        binding.IDKodePenTxtNamaLengkap.text = data.getDataBlankoSatu().namaLengkap
        binding.IDKodePenTxtTTL.text = data.getDataBlankoSatu().tanggalLahir
        binding.IDKodePenTxtAgama.text = data.getDataBlankoSatu().agama
        binding.IDKodePenTxtKebangsaan.text = data.getDataBlankoSatu().kebangsaan
        binding.IDKodePenJenisKelamin.text = data.getDataBlankoSatu().jenisKelamin
        binding.IDKodePenTxtStatus.text = data.getDataBlankoSatu().status
        binding.IDKodePenTxtPekerjaan.text = data.getDataBlankoSatu().pekerjaan
        binding.IDKodePenTxtAlamat.text = data.getDataBlankoSatu().alamat
        binding.IDKodePenTxtKTP.text = data.getDataBlankoSatu().noKtp
        binding.IDKodePenTxtKK.text = data.getDataBlankoSatu().noKK
        binding.IDKodePenTxtTelp.text = data.getDataBlankoSatu().noTelp

        binding.IDKodePenTxtNamaBapak.text = data.getDataBlankoDua().namaBapak
        binding.IDKodePenTxtTTLBapak.text = data.getDataBlankoDua().tanggalLahirAyah
        binding.IDKodePenTxtAgamaBapak.text = data.getDataBlankoDua().agamaBapak
        binding.IDKodePenTxtKebangsaanBapak.text = data.getDataBlankoDua().kebangsaanBapak
        binding.IDKodePenTxtStatusBapak.text = data.getDataBlankoDua().statusBapak
        binding.IDKodePenTxtPekerjaanBapak.text = data.getDataBlankoDua().pekerjaanBapak
        binding.IDKodePenTxtAlamatBapak.text = data.getDataBlankoDua().alamatBapak

        binding.IDKodePenTxtNamaIbu.text = data.getDataBlankoDua().namaIbu
        binding.IDKodePenTxtTTLIbu.text = data.getDataBlankoDua().tanggalLahirIbu
        binding.IDKodePenTxtAgamaIbu.text = data.getDataBlankoDua().agamaIbu
        binding.IDKodePenTxtKebangsaanIbu.text = data.getDataBlankoDua().kebangsaanIbu
        binding.IDKodePenTxtStatusIbu.text = data.getDataBlankoDua().statusIbu
        binding.IDKodePenTxtPekerjaanIbu.text = data.getDataBlankoDua().pekerjaanIbu
        binding.IDKodePenTxtAlamatIbu.text = data.getDataBlankoDua().alamatIbu



    }

    override fun onResume() {
        super.onResume()
        setData()
    }

}