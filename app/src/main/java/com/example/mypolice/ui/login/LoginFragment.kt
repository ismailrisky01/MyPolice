package com.example.mypolice.ui.login

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentLoginBinding
import com.example.mypolice.utils.LoadingHelper
import com.example.mypolice.utils.MyFragment
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : MyFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val loading = LoadingHelper(requireContext())
        binding.IDLoginBtnLogin.setOnClickListener {
            loading.show()
            val email = binding.IDLoginEdtEmail.text.toString()
            val password = binding.IDLoginEdtPassword.text.toString()
            Toast.makeText(requireContext(), email+password, Toast.LENGTH_SHORT).show()
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(requireActivity()) {
                if (it.isSuccessful){
                    val user = FirebaseAuth.getInstance().currentUser
                    LoadingHelper(requireContext()).dismiss()

                    if (user!=null) {
                        if (user.isEmailVerified) {
                            loading.dismiss()
                            Toast.makeText(requireContext(), "Succes Login", Toast.LENGTH_SHORT)
                                .show()
                            findNavController().navigate(R.id.action_loginFragment_to_dashboardFragment)
                        }else{
                            loading.dismiss()
                            LoadingHelper(requireContext()).dismiss()

                            Toast.makeText(requireContext(), "Please Verified Your email", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }else{
                    loading.dismiss()
                    Log.w("Login", "signInWithEmail:failure", it.exception)
                    Toast.makeText(requireContext(), "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }


        }
        binding.IDLoginBtnSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrasiFragment)
        }
    }


}