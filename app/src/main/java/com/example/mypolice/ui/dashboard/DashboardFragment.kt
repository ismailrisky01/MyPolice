package com.example.mypolice.ui.dashboard

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.mypolice.MainActivity
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentDashboardBinding
import com.example.mypolice.utils.MyFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.dialogs.SettingsDialog

class DashboardFragment : MyFragment<FragmentDashboardBinding>(R.layout.fragment_dashboard),
    View.OnClickListener, EasyPermissions.PermissionCallbacks {
    companion object {

        private val PERMISSION_AUDIO_AND_CALL =
            arrayOf(Manifest.permission.RECORD_AUDIO, Manifest.permission.CALL_PHONE)
        const val PERMISSION_LOCATION_REQUEST_CODE = 1
        const val PERMISSION_AUDIO_REQUEST_CODE = 2
        const val PERMISSION_CALL_REQUEST_CODE = 3
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = FirebaseAuth.getInstance().currentUser
        if (user == null) {
            findNavController().navigate(R.id.action_dashboardFragment_to_loginFragment)
        }else{

        }



        binding.IDDashboardCardView1.setOnClickListener(this)
        binding.IDDashboardCardView2.setOnClickListener(this)
        binding.IDDashboardCardView3.setOnClickListener(this)
        binding.IDDashboardCardView4.setOnClickListener(this)
        binding.IDDashboardCardView5.setOnClickListener(this)
        binding.IDDashboardCardView6.setOnClickListener(this)
        binding.IDDashboardCardView7.setOnClickListener(this)
        binding.IDDashboardWelcome.text = "Welcome " + user?.displayName


    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ID_Dashboard_CardView1 -> checkPermission()
            R.id.ID_Dashboard_CardView2 -> findNavController().navigate(R.id.action_dashboardFragment_to_trackRecordFragment)
            R.id.ID_Dashboard_CardView3 -> findNavController().navigate(R.id.action_dashboardFragment_to_dokumenKendaraanFragment)
            R.id.ID_Dashboard_CardView4 -> findNavController().navigate(R.id.action_dashboardFragment_to_ETilangFragment)
            R.id.ID_Dashboard_CardView5 -> findNavController().navigate(R.id.action_dashboardFragment_to_SKCKFragment)
            R.id.ID_Dashboard_CardView6 -> findNavController().navigate(R.id.action_dashboardFragment_to_daftarPolisiFragment2)
            R.id.ID_Dashboard_CardView7 -> findNavController().navigate(R.id.action_dashboardFragment_to_monitorFragment)

        }
    }
    fun checkPermission(){
        Dexter.withActivity(requireActivity()).withPermissions(Manifest.permission.RECORD_AUDIO,Manifest.permission.ACCESS_FINE_LOCATION)
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    if (report.areAllPermissionsGranted()) {
                        findNavController().navigate(R.id.action_dashboardFragment_to_haloPolFragment)
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
            }).onSameThread().check()
    }
    private fun showSettingsDialog() {
        MaterialAlertDialogBuilder(requireContext()).setCancelable(false)
            .setMessage("Anda Telah mematikan permission secara permanen, hidupkan permissin di setting handphone")
            .setPositiveButton("Hidupkan") { dialog, which ->
                findNavController().popBackStack()
            }.show()
    }
    private fun hasLocationPermission() =
        EasyPermissions.hasPermissions(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        )

    private fun requestLocationPermission() {
        EasyPermissions.requestPermissions(
            this,
            "This application cannot work without Location Permission.",
            PERMISSION_LOCATION_REQUEST_CODE,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        if (EasyPermissions.somePermissionDenied(requireActivity(), perms.first())) {
            SettingsDialog.Builder(requireActivity()).build().show()
        } else {
            requestLocationPermission()
        }
    }


    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        Toast.makeText(
            requireContext(),
            "Permission Granted!",
            Toast.LENGTH_SHORT
        ).show()
        findNavController().navigate(R.id.action_dashboardFragment_to_haloPolFragment)
    }

}