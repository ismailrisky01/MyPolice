package com.example.mypolice.ui.login

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentLoginBinding
import com.example.mypolice.utils.LoadingHelper
import com.example.mypolice.utils.MyFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import es.dmoral.toasty.Toasty


class LoginFragment : MyFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val loading = LoadingHelper(requireContext())

        binding.IDLoginBtnLogin.setOnClickListener {
            loading.show()
            val email = binding.IDLoginEdtEmail.text.toString()
            val password = binding.IDLoginEdtPassword.text.toString()
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity()) {
                    if (it.isSuccessful) {
                        val user = FirebaseAuth.getInstance().currentUser
                        LoadingHelper(requireContext()).dismiss()

                        if (user != null) {
                            if (user.isEmailVerified) {
                                loading.dismiss()
                                Toast.makeText(requireContext(), "Succes Login", Toast.LENGTH_SHORT)
                                    .show()
//                                val intent = Intent(activity?.baseContext, MainActivity::class.java)
//                                activity?.startActivity(intent)
                                checkPermission(findNavController().navigate(R.id.action_loginFragment_to_dashboardFragment))
                            } else {
                                loading.dismiss()
                                LoadingHelper(requireContext()).dismiss()

                                Toast.makeText(
                                    requireContext(),
                                    "Please Verified Your email",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                        }
                    } else {
                        loading.dismiss()
                        Log.w("Login", "signInWithEmail:failure", it.exception)
                        Toast.makeText(
                            requireContext(), "Authentication failed.${it.exception?.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }


        }
        binding.IDLoginBtnSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrasiFragment)
        }
        binding.IDLoginBtnForgetPassword.setOnClickListener {
            val email = binding.IDLoginEdtEmail.text.toString()
            if (email == "") {
                Toasty.warning(requireContext(), "Please fill email", Toasty.LENGTH_SHORT).show()
            }else{
                loading.show()
                FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnSuccessListener {
                    loading.dismiss()
                }.addOnFailureListener {
                    loading.dismiss()
                    Toasty.error(requireContext(),"${it.message}",Toasty.LENGTH_SHORT).show()
                }

            }
        }
    }

    fun checkPermission(s: Unit) {
        Dexter.withActivity(requireActivity()).withPermissions(
            Manifest.permission.RECORD_AUDIO
        )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    if (report.areAllPermissionsGranted()) {
                        s
                    }

                    // check for permanent denial of any permission
                    if (report.isAnyPermissionPermanentlyDenied()) {
                        showSettingsDialogPermission()
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: MutableList<PermissionRequest>?,
                    token: PermissionToken?
                ) {
                    token?.continuePermissionRequest()
                }
            }).onSameThread().check()
    }

    fun showSettingsDialogPermission() {
        MaterialAlertDialogBuilder(requireContext()).setCancelable(false)
            .setMessage("Anda Telah mematikan permission secara permanen, hidupkan permissin di setting handphone")
            .setPositiveButton("Hidupkan") { _, _ ->
            }.show()
    }

}