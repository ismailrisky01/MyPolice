package com.example.mypolice.ui.dashboard.halo_polisi.bencana

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentHaloBencanaBinding
import com.example.mypolice.databinding.FragmentHaloFormLaporanBinding
import com.example.mypolice.model.ModelBencana
import com.example.mypolice.ui.dashboard.halo_polisi.form_laporan.HaloFromLaporanViewModel
import com.example.mypolice.ui.profile.ProfileViewModel
import com.example.mypolice.utils.DatePickerMe
import com.example.mypolice.utils.MyFragment
import java.io.FileDescriptor
import java.io.IOException


class HaloBencanaFragment : MyFragment<FragmentHaloBencanaBinding>(R.layout.fragment_halo_bencana) {
    var selectedPhotoUri: Uri? = null
    private lateinit var mHaloFormBencanaViewModel: HaloBencanaViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mHaloFormBencanaViewModel = ViewModelProvider(this).get(HaloBencanaViewModel::class.java)

        binding.IDHaloBencanaImgPreview.visibility = View.GONE
        binding.IDHaloBencanaBtnUpload.setOnClickListener {
            getImage()
        }
        binding.IDHaloBencanaBtnBack.setOnClickListener {
            findNavController().navigate(R.id.action_haloBencanaFragment_to_haloPolFragment)
        }
        binding.IDHaloBencanaBtnSerahkan.setOnClickListener {
            if (selectedPhotoUri != null) {
                mHaloFormBencanaViewModel.uploadImageBencana(selectedPhotoUri!!)
                    .observe(viewLifecycleOwner,
                        Observer {
                            val jenisBencana = binding.IDHaloBencanaEdtJenisBencana.text.toString()
                            val tanggalBencana =
                                binding.IDHaloBencanaEdtTanggalBencana.text.toString()
                            val waktuKejadian = binding.IDHaloBencanaEdtWaktuBencana.text.toString()
                            val kronologi = binding.IDHaloBencanaEdtKronologi.text.toString()

                            val data = ModelBencana(
                                "",
                                jenisBencana,
                                tanggalBencana,
                                waktuKejadian,
                                kronologi,
                                it
                            )
                            mHaloFormBencanaViewModel.uploadDataBencana(data, requireContext()).run {
                                binding.IDHaloBencanaEdtJenisBencana.setText("")
                                binding.IDHaloBencanaEdtTanggalBencana.setText("")
                                binding.IDHaloBencanaEdtWaktuBencana.setText("")
                                binding.IDHaloBencanaEdtKronologi.setText("")
                                binding.IDHaloBencanaImgPreview.visibility = View.GONE
                            }
                        })
            } else {
                Toast.makeText(requireContext(), "Pilih Foto Dahulu", Toast.LENGTH_SHORT).show()
            }
        }
        binding.IDHaloBencanaBtnSimpan.setOnClickListener {

        }
        binding.IDHaloBencanaEdtTanggalBencana.setOnClickListener {
            val date = DatePickerMe().getDatePicker(requireContext())
            binding.IDHaloBencanaEdtTanggalBencana.setText(date)
        }
    }

    private fun getImage() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(intent, 101)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 101 && data != null) {
            selectedPhotoUri = data.data
            val bitmap = getBitmapFromUri(selectedPhotoUri!!)
            binding.IDHaloBencanaImgPreview.setImageBitmap(bitmap)
            binding.IDHaloBencanaImgPreview.visibility = View.VISIBLE


        }

    }

    @Throws(IOException::class)
    fun getBitmapFromUri(uri: Uri): Bitmap {
        val contentResolver = requireContext().contentResolver!!
        val parcelFileDescriptor: ParcelFileDescriptor? =
            contentResolver.openFileDescriptor(uri, "r")
        val fileDescriptor: FileDescriptor = parcelFileDescriptor!!.fileDescriptor
        val image: Bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor)
        parcelFileDescriptor.close()
        return image
    }

}