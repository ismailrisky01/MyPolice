package com.example.mypolice

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.mypolice.databinding.FragmentBlankoDuaBinding
import com.example.mypolice.utils.MyFragment


class BlankoDuaFragment : MyFragment<FragmentBlankoDuaBinding>(R.layout.fragment_blanko_dua) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.IDBlankoDuaBtnLanjut.setOnClickListener {
            findNavController().navigate(R.id.action_blankoDuaFragment_to_blankoTigaFragment)
        }
    }
}