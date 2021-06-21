package com.example.mypolice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mypolice.databinding.FragmentBlankoSatuBinding
import com.example.mypolice.databinding.FragmentDashboardBinding
import com.example.mypolice.utils.MyFragment


class BlankoSatuFragment : MyFragment<FragmentBlankoSatuBinding>(R.layout.fragment_blanko_satu) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.IDBlankoSatuBtnLanjut.setOnClickListener {
            findNavController().navigate(R.id.action_blankoSatuFragment_to_blankoDuaFragment)
        }
    }
}