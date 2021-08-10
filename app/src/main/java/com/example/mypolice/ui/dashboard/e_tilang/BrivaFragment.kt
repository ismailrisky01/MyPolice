package com.example.mypolice.ui.dashboard.e_tilang

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentBrivaBinding
import com.example.mypolice.utils.MyFragment


class BrivaFragment : MyFragment<FragmentBrivaBinding>(R.layout.fragment_briva) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.IDBrivaBtnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}