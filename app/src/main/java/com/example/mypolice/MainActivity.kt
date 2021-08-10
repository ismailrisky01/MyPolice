package com.example.mypolice

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.mypolice.databinding.ActivityMainBinding
import com.example.mypolice.utils.CallHelper
import com.example.mypolice.utils.LoadingHelper
import com.example.mypolice.utils.Reload
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.sinch.android.rtc.PushPair
import com.sinch.android.rtc.Sinch
import com.sinch.android.rtc.SinchClient
import com.sinch.android.rtc.SinchError
import com.sinch.android.rtc.calling.Call
import com.sinch.android.rtc.calling.CallClient
import com.sinch.android.rtc.calling.CallClientListener
import com.sinch.android.rtc.calling.CallListener

class MainActivity : AppCompatActivity(), CallListener, CallClientListener {
    private lateinit var binding: ActivityMainBinding
    private val APP_KEY = "557a34c6-06ee-4980-9e5c-b1072ffb985b"
    private val APP_SECRET = "mg/uJUvUZUOZgzv/cVvSfA=="
    private val ENVIRONMENT = "sandbox.sinch.com"
    private val loud = false
    private var call: Call? = null
    lateinit var sinchClient: SinchClient
    lateinit var loading: CallHelper

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(name, context, attrs)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loading = CallHelper(this)
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.nav_host_fragment)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.loginFragment || destination.id == R.id.splashScreenFragment || destination.id == R.id.registrasiFragment) {
                bottomNavigation.visibility = View.GONE
                reset()
            } else {
                bottomNavigation.visibility = View.VISIBLE
                refresh()
            }
        }
        bottomNavigation.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.dashboardFragment || destination.id == R.id.listContactFragment || destination.id == R.id.chatFragment || destination.id == R.id.profileFragment) {
                bottomNavigation.visibility = View.VISIBLE
            } else {
                bottomNavigation.visibility = View.GONE
            }
        }


    }

    fun reset() {
        sinchClient = Sinch.getSinchClientBuilder()
            .context(this)
            .userId("user")
            .applicationKey(APP_KEY)
            .applicationSecret(APP_SECRET)
            .environmentHost(ENVIRONMENT)
            .build()
//        sinchClient.stopListeningOnActiveConnection()
        sinchClient.stop()
    }

    fun refresh() {
        val user = FirebaseAuth.getInstance().currentUser?.uid
        if (user != null) {
            if (sinchClient.isStarted) {
            } else {
                sinchClient = Sinch.getSinchClientBuilder()
                    .context(this)
                    .userId(user)
                    .applicationKey(APP_KEY)
                    .applicationSecret(APP_SECRET)
                    .environmentHost(ENVIRONMENT)
                    .build()

                sinchClient.setSupportCalling(true)
                sinchClient.startListeningOnActiveConnection()
                sinchClient.start()
                sinchClient.getCallClient().addCallClientListener(this)
            }

        }
    }

    override fun onCallProgressing(p0: Call?) {
        TODO("Not yet implemented")
    }

    override fun onCallEstablished(p0: Call?) {
        Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show()
        sinchClient.audioController.enableSpeaker()
        loading.show()

    }

    override fun onCallEnded(p0: Call?) {
        call = null
//        val a: SinchError? = p0?.getDetails()?.getError()
        loading.dismiss()
//        binding.button.setText("Call")
//        binding.txtKet.setText("")
    }

    override fun onShouldSendPushNotification(p0: Call?, p1: MutableList<PushPair>?) {

    }

    override fun onIncomingCall(p0: CallClient?, p1: Call?) {
        call = p1
        Toast.makeText(this, "incoming call", Toast.LENGTH_SHORT).show()
        call!!.answer()
        call!!.addCallListener(this)
    }

    private fun showSettingsDialog() {
        MaterialAlertDialogBuilder(this).setCancelable(false)
            .setMessage("Anda Terhubung dengan kepolisian")
            .setPositiveButton("Matikan") { _, which ->
                if (call != null) {
                    call!!.hangup()
                }
            }.show()
    }


}