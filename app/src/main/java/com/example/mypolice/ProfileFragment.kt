package com.example.mypolice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.mypolice.model.ModelUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class ProfileFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = FirebaseAuth.getInstance().currentUser?.uid
        val ref = FirebaseFirestore.getInstance().collection("MyPolice").document("UserAccount/$user/DataDiri")
        ref.get().addOnSuccessListener { document ->
            if (document.exists()){
                Toast.makeText(requireContext(), "Ada Dongg", Toast.LENGTH_SHORT).show()
            }else{
                ref.set(ModelUser(user!!,"Ismail Riksy","28-03-1999","Ngawi","352110000000","352110000000","000000000")).addOnSuccessListener{
                    Toast.makeText(requireContext(), "Succes", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(requireContext(), "Error: ${it.toString()}", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}