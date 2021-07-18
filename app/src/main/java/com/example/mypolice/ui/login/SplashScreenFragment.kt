package com.example.mypolice.ui.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentSplashScreenBinding
import com.example.mypolice.utils.LoadingHelper
import com.example.mypolice.utils.MyFragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashScreenFragment :
    MyFragment<FragmentSplashScreenBinding>(R.layout.fragment_splash_screen) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    fun init(){
        GlobalScope.launch {
            val user = FirebaseAuth.getInstance().currentUser
            delay(5000L)
            requireActivity().runOnUiThread {
                if (user != null) {
                    if (user.isEmailVerified) {
                        findNavController().navigate(R.id.action_splashScreenFragment_to_dashboardFragment)
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

    override fun onResume() {
        super.onResume()
        init()
    }
}