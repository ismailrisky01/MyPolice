package com.example.mypolice.ui.profile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentProfileBinding
import com.example.mypolice.model.ModelUser
import com.example.mypolice.ui.dashboard.track_record.TrackRecordViewModel
import com.example.mypolice.utils.LoadingHelper
import com.example.mypolice.utils.MyFragment
import com.example.mypolice.utils.SharedPref
import com.example.mypolice.utils.logD
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.MutableData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import java.util.*


class ProfileFragment : MyFragment<FragmentProfileBinding>(R.layout.fragment_profile) {
    private lateinit var mProfileViewmodel: ProfileViewModel
    var imageUser = String()
    var selectedPhotoUri: Uri? = null
    val user = FirebaseAuth.getInstance().currentUser!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mProfileViewmodel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        setData()
        val myshared = SharedPref(requireContext())
        val ktp =myshared.getDataUser().noKTP
        binding.IDProfileImageUser.setOnClickListener { getImage() }
        binding.IDProfileNamaUser.text = user.displayName
        binding.IDProfileBtnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
        }
        binding.IDProfileBtnUpadte.setOnClickListener {
            update()
        }
    }




    private fun getImage() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(intent, 100)
    }

    private fun update() {
        val loading = LoadingHelper(requireContext())
        loading.show()
        val nama = binding.IDProfileEdtNama.text.toString()
        val ttl = binding.IDProfileEdtTtl.text.toString()
        val alamat = binding.IDProfileEdtAlamat.text.toString()
        val ktp = binding.IDProfileEdtNoKtp.text.toString()
        val sim = binding.IDProfileEdtNoSim.text.toString()
        val stnk = binding.IDProfileEdtNoSTNK.text.toString()
        if (selectedPhotoUri == null) {
            val data = ModelUser(
                user.uid,
                imageUser,
                nama,
                ttl,
                alamat,
                ktp,
                sim,
                stnk
            )
            mProfileViewmodel.updateData(data, requireContext()).run {
                loading.dismiss()
            }
        } else {
            if (imageUser.isEmpty()) {
                uploadImage(selectedPhotoUri).observe(viewLifecycleOwner, Observer {
                    val data = ModelUser(
                        user.uid,
                        it,
                        nama,
                        ttl,
                        alamat,
                        ktp,
                        sim,
                        stnk
                    )
                    mProfileViewmodel.updateData(
                        data, requireContext()
                    ).run {
                        loading.dismiss()
                    }
                })
            } else {
                FirebaseStorage.getInstance().getReferenceFromUrl(imageUser).delete()
                    .addOnSuccessListener {
                        uploadImage(selectedPhotoUri).observe(viewLifecycleOwner, Observer {
                            val data = ModelUser(
                                user.uid,
                                it,
                                nama,
                                ttl,
                                alamat,
                                ktp,
                                sim,
                                stnk
                            )
                            mProfileViewmodel.updateData(
                                data, requireContext()
                            )
                        })
                        loading.dismiss()

                    }.addOnFailureListener {
                        logD(it.message)
                    }
            }

        }
    }

    fun setData() {

        mProfileViewmodel.getDataProfile(requireContext()).observe(viewLifecycleOwner, Observer {
            val myPref = SharedPref(requireContext())
            myPref.setDataUser(it)
            logD("Try Get Data Profile")
            if (it.imageProfile == "") {

                logD("Gaenk Gambar")
                binding.IDProfileImageUser.setImageResource(R.drawable.image16)
            } else {

                imageUser = it.imageProfile
                logD("enek gambar $imageUser")
                Picasso.get().load(it.imageProfile).into(binding.IDProfileImageUser)
            }

            binding.IDProfileEdtNama.setText(it.nama)
            binding.IDProfileEdtNoKtp.setText(it?.noKTP)
            binding.IDProfileEdtTtl.setText(it.ttl)
            binding.IDProfileEdtAlamat.setText(it.alamat)
            binding.IDProfileEdtNoSim.setText(it.noSIM)
            binding.IDProfileEdtNoSTNK.setText(it.noRegistrasiSTNK)
        })
    }


    private fun uploadImage(imageUri: Uri?): MutableLiveData<String> {
        val filename = UUID.randomUUID().toString()
        val storage = FirebaseStorage.getInstance().getReference("/MyPolice/$filename")
        var uri = MutableLiveData<String>()
        storage.putFile(imageUri!!).addOnSuccessListener {
            storage.downloadUrl.addOnSuccessListener {
                logD("Disimpan ke ${it.toString()}")
                uri.value = it.toString()
            }

        }
        return uri

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
if (resultCode==Activity.RESULT_OK&&requestCode==100&&data!=null){
    selectedPhotoUri = data.data
    val bitmap = MediaStore.Images.Media.getBitmap(requireContext().contentResolver, selectedPhotoUri)
    binding.IDProfileImageUser.setImageBitmap(bitmap)
}
    }

}