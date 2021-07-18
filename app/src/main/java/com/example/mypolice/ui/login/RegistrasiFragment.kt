package com.example.mypolice.ui.login

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentETilangBinding
import com.example.mypolice.databinding.FragmentRegistrasiBinding
import com.example.mypolice.utils.LoadingHelper
import com.example.mypolice.utils.MyFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest


class RegistrasiFragment : MyFragment<FragmentRegistrasiBinding>(R.layout.fragment_registrasi) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val loading= LoadingHelper(requireContext())

        binding.IDRegisBtnRegis.setOnClickListener {
            loading.show()
            val username = binding.IDRegisEdtUsername.text.toString()
            val email = binding.IDRegisEdtEmail.text.toString()
            val password = binding.IDRegisEdtPassword.text.toString()
            val confirmpassword = binding.IDRegisEdtConfirm.text.toString()
            if (password==confirmpassword){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            val user = FirebaseAuth.getInstance().currentUser
                            user!!.sendEmailVerification()
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                       loading.dismiss()

                                        Log.d(TAG, "Email sent.")
                                        Log.d("Registrasi", "createUserWithEmail:success")
                                        val profileUpdates = userProfileChangeRequest {
                                            displayName = username
                                        }
                                        user.updateProfile(profileUpdates).addOnCompleteListener {
                                            findNavController().navigate(R.id.action_registrasiFragment_to_loginFragment)
                                        }

                                    }
                                }

                        } else {
                            loading.dismiss()

                            Log.w("Registrasi", "createUserWithEmail:failure", it.exception)
                            Toast.makeText(
                                requireContext(), "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }else{
                loading.dismiss()
                Toast.makeText(requireContext(), "Password tidak sama", Toast.LENGTH_SHORT).show()
            }

        }
    }


}