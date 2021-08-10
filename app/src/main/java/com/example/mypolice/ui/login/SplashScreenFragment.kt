package com.example.mypolice.ui.login

import android.Manifest
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentSplashScreenBinding
import com.example.mypolice.utils.LoadingHelper
import com.example.mypolice.utils.MyFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashScreenFragment :
    MyFragment<FragmentSplashScreenBinding>(R.layout.fragment_splash_screen) {

    fun init(){

        GlobalScope.launch {
            FirebaseApp.initializeApp(requireContext())

            val user = FirebaseAuth.getInstance().currentUser
            delay(3000L)
            requireActivity().runOnUiThread {
                if (user != null) {
                    if (user.isEmailVerified) {
                        checkPermission(findNavController().navigate(R.id.action_splashScreenFragment_to_dashboardFragment))

                    } else {
                        findNavController().navigate(R.id.action_splashScreenFragment_to_loginFragment)
                        Toast.makeText(
                            requireContext(),
                            "Please Verified Your Email",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    findNavController().navigate(R.id.action_splashScreenFragment_to_loginFragment)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
    fun checkPermission(s:Unit){
        Dexter.withActivity(requireActivity()).withPermissions(
            Manifest.permission.RECORD_AUDIO)
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
            .setPositiveButton("Hidupkan") { dialog, which ->
            }.show()
    }
    override fun onResume() {
        super.onResume()
        init()
    }
}