package com.example.mypolice.ui.call

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentListContactBinding
import com.example.mypolice.ui.dashboard.DashboardFragment
import com.example.mypolice.utils.MyFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.dialogs.SettingsDialog


class ListContactFragment : MyFragment<FragmentListContactBinding>(R.layout.fragment_list_contact){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.IDListContactCardview1.visibility = View.GONE
        binding.IDListContactCardview2.visibility = View.GONE
        checkPermission()
    }

    fun checkPermission(){
        Dexter.withActivity(requireActivity()).withPermissions(
            Manifest.permission.RECORD_AUDIO,Manifest.permission.CALL_PHONE)
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    if (report.areAllPermissionsGranted()) {
                        binding.IDListContactCardview1.visibility = View.VISIBLE
                        binding.IDListContactCardview2.visibility = View.VISIBLE
                        binding.IDListContactCardview1.setOnClickListener {
                            val bundle = Bundle()
                            bundle.putString("key","admin")
                            findNavController().navigate(R.id.action_listContactFragment_to_callTwoFragment,bundle)
                        }

                        binding.IDListContactCardview2.setOnClickListener {
                            val bundle = Bundle()
                            bundle.putString("key","admin")
                            findNavController().navigate(R.id.action_listContactFragment_to_callTwoFragment,bundle)
                        }
                    }

                    // check for permanent denial of any permission
                    if (report.isAnyPermissionPermanentlyDenied()) {
                        showSettingsDialog()
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: MutableList<PermissionRequest>?,
                    token: PermissionToken?
                ) {
                    token?.continuePermissionRequest()
                }
            }).check()
    }
    private fun showSettingsDialog() {
        MaterialAlertDialogBuilder(requireContext()).setCancelable(false)
            .setMessage("Anda Telah mematikan permission secara permanen, hidupkan permissin di setting handphone")
            .setPositiveButton("Hidupkan") { dialog, which ->
                findNavController().popBackStack()
            }.show()
    }

}