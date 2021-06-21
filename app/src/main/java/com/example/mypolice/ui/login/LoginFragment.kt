package com.example.mypolice.ui.login

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentLoginBinding
import com.example.mypolice.utils.MyFragment


class LoginFragment : MyFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.IDLoginBtnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_dashboardFragment)

        }
        binding.IDLoginBtnSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrasiFragment)
        }
    }


}