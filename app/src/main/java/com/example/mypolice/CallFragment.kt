package com.example.mypolice

import android.media.AudioManager
import android.os.Bundle
import android.view.View
import com.example.mypolice.databinding.FragmentCallBinding
import com.example.mypolice.utils.MyFragment
import com.google.firebase.auth.FirebaseAuth
import com.sinch.android.rtc.PushPair
import com.sinch.android.rtc.Sinch
import com.sinch.android.rtc.SinchClient
import com.sinch.android.rtc.calling.Call
import com.sinch.android.rtc.calling.CallClient
import com.sinch.android.rtc.calling.CallClientListener
import com.sinch.android.rtc.calling.CallListener


class CallFragment : MyFragment<FragmentCallBinding>(R.layout.fragment_call) {
    private val APP_KEY = "c0c2bd70-8853-4bcd-baee-f1e09a0a6aef"
    private val APP_SECRET = "kw5JAt+SAEqcBmaXx3ZIAw=="
    private val ENVIRONMENT = "sandbox.sinch.com"
    private var sinchClient: SinchClient? = null
    private val call: Call? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sinchClient = Sinch.getSinchClientBuilder()
            .context(requireContext())
            .userId(FirebaseAuth.getInstance().uid)
            .applicationKey(APP_KEY)
            .applicationSecret(APP_SECRET)
            .environmentHost(ENVIRONMENT)
            .build()

        sinchClient?.setSupportCalling(true)
        sinchClient?.startListeningOnActiveConnection()
        sinchClient?.start()




    }



}