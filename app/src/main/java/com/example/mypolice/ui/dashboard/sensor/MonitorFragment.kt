package com.example.mypolice.ui.dashboard.sensor

import android.Manifest
import android.app.AlertDialog
import android.content.DialogInterface
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentDashboardBinding
import com.example.mypolice.databinding.FragmentMonitorBinding
import com.example.mypolice.model.ModelSensor
import com.example.mypolice.model.ModelSensorKu
import com.example.mypolice.utils.LoadingHelper
import com.example.mypolice.utils.MyFragment
import com.example.mypolice.utils.logD
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.Delay
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MonitorFragment : MyFragment<FragmentMonitorBinding>(R.layout.fragment_monitor) {
    private val mSensorViewModel by lazy {
        ViewModelProvider(this).get(SensorViewModel::class.java)
    }
    private var mediaPlayer: MediaPlayer? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData()
        binding.IDMonitorSensorBtnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.IDMonitorSensorBtnGuide.setOnClickListener {
            findNavController().navigate(R.id.action_monitorFragment_to_sensorGuideFragment)
        }
        binding.IDMonitorSensorBtnMonitor.setOnClickListener {
            findNavController().navigate(R.id.action_monitorFragment_to_sensorMonitorFragment)
        }
        binding.IDMonitorBtnPairing.setOnClickListener {
            checkPermission()
        }

    }

    fun checkPermission(){
        Dexter.withActivity(requireActivity()).withPermissions(
            Manifest.permission.CAMERA)
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    if (report.areAllPermissionsGranted()) {
                        findNavController().navigate(R.id.action_monitorFragment_to_scannerQrFragment)

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

    fun getData() {
        val loading = LoadingHelper(requireContext())
        loading.show()
        FirebaseFirestore.getInstance().collection("MyPolice")
            .document("UserAccount/${FirebaseAuth.getInstance().currentUser?.uid}/Sensor")
            .get().addOnSuccessListener { document ->
                if (document.exists()) {
                    loading.dismiss()
                    val data = document.toObject(ModelSensorKu::class.java) as ModelSensorKu
                    mSensorViewModel.getSensor(requireContext(), data.namaSensor)
                        .observe(viewLifecycleOwner, Observer { data ->
                            binding.IDMonitorTxtNamaSensor.text = data.namaAlat
                            mediaPlayer = MediaPlayer.create(requireContext(), R.raw.beep)
                            mediaPlayer?.setOnPreparedListener {
                                logD("Ready to GO")
                            }
                            if (data.status&&data.statusKecelakaan=="TBKP") {
                                mediaPlayer?.start()
                                mediaPlayer?.isLooping = true
                                Toasty.warning(requireContext(), "Waning", Toasty.LENGTH_LONG)
                                    .show()
                                GlobalScope.launch {
                                    (1..10).forEach {
                                        delay(1000)
                                        if (it==10){
                                            activity?.runOnUiThread {
                                                dialog(data.namaAlat)
                                            }
                                        }
                                        if (it == 10) {
                                            mediaPlayer?.stop()
                                        }
                                    }

                                }
                                loading.dismiss()

                            } else {
                                mediaPlayer?.isLooping = false
                                mediaPlayer?.stop()
                                mediaPlayer?.seekTo(0)
                                loading.dismiss()
                            }
                        })
                } else {
                    Toasty.warning(requireContext(), "Please Pairing first", Toasty.LENGTH_LONG)
                        .show()
                    loading.dismiss()
                }
            }
    }

    private fun dialog(namaAlat: String) {
        MaterialAlertDialogBuilder(requireContext()).setCancelable(false)
            .setMessage("Apakah Anda Ingin Melaporkan kepada kepolisian?")
            .setNegativeButton("Tidak") { dialog, which ->
            }
            .setPositiveButton("Laporkan") { dialog, which ->
                val childUpdates = hashMapOf<String, Any>(
                    "/idPengemudi" to FirebaseAuth.getInstance().currentUser?.uid!!,
                    "/statusKecelakaan" to "TSKP",
                    "/namaPengemudi" to FirebaseAuth.getInstance().currentUser?.displayName!!

                )
                FirebaseDatabase.getInstance().reference.child("MyPolice/sensor/$namaAlat")
                    .updateChildren(childUpdates).addOnSuccessListener {
                        Toasty.success(
                            requireContext(),
                            "Berhasil Di Laporkan",
                            Toasty.LENGTH_SHORT
                        )
                            .show()
                    }
            }.show()

    }
}