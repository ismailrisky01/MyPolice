package com.example.mypolice.ui.call

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentListContactBinding
import com.example.mypolice.utils.MyFragment


class ListContactFragment : MyFragment<FragmentListContactBinding>(R.layout.fragment_list_contact) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.IDListContactCardview1.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("key","admin")
            findNavController().navigate(R.id.action_listContactFragment_to_callTwoFragment,bundle)
        }
    }
}