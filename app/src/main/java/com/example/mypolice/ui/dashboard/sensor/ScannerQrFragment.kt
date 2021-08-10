package com.example.mypolice.ui.dashboard.sensor

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.example.mypolice.databinding.FragmentScannerQrBinding
import com.example.mypolice.model.ModelSensorKu
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import es.dmoral.toasty.Toasty


class ScannerQrFragment : Fragment() {
//
    lateinit var binding: FragmentScannerQrBinding
    private lateinit var codeScanner: CodeScanner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentScannerQrBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        codeScanner()
    }

    private fun codeScanner() {
        codeScanner = CodeScanner(requireContext(), binding.scn)

        codeScanner.apply {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS

            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.SINGLE
            isAutoFocusEnabled = true
            isFlashEnabled = false

            decodeCallback = DecodeCallback {
                activity?.runOnUiThread {
                    Toast.makeText(requireContext(), ""+ it.text, Toast.LENGTH_SHORT).show()
                    FirebaseFirestore.getInstance().collection("MyPolice")
                        .document("UserAccount/${FirebaseAuth.getInstance().currentUser?.uid}/Sensor").set(ModelSensorKu(it.text)).addOnSuccessListener {
                            findNavController().popBackStack()
                        }.addOnFailureListener {
                            Toasty.warning(requireContext(),it.message.toString(),Toasty.LENGTH_SHORT).show()
                            findNavController().popBackStack()
                        }


                }
            }

            errorCallback = ErrorCallback {
                activity?.runOnUiThread {
                    Log.e("Main", "codeScanner: ${it.message}")

                    findNavController().popBackStack()
                }
            }

            binding.scn.setOnClickListener {
                codeScanner.startPreview()
            }

        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }


    companion object {
        private const val CAMERA_REQ = 101
    }
}