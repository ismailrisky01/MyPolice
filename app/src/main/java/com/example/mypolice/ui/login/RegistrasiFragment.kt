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
import com.example.mypolice.utils.MyFragment
import com.google.firebase.auth.FirebaseAuth


class RegistrasiFragment : MyFragment<FragmentRegistrasiBinding>(R.layout.fragment_registrasi) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
binding.button.setOnClickListener {
    val email = binding.IDRegisEdtEmail.text.toString()
    val password = binding.IDRegisEdtPassword.text.toString()
    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener {
        if (it.isSuccessful){
            Log.d("Registrasi", "createUserWithEmail:success")
            findNavController().navigate(R.id.action_registrasiFragment_to_dashboardFragment)
        }else{
            Log.w("Registrasi", "createUserWithEmail:failure", it.exception)
            Toast.makeText(requireContext(), "Authentication failed.",
                Toast.LENGTH_SHORT).show()
        }
    }
}
    }

}