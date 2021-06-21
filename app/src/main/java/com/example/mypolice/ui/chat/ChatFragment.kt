package com.example.mypolice.ui.chat

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentChatBinding
import com.example.mypolice.model.ModelChat
import com.example.mypolice.utils.MyFragment

class ChatFragment : MyFragment<FragmentChatBinding>(R.layout.fragment_chat) {
    private lateinit var mChatViewModel: ChatViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ChatAdapter()
        binding.IDChatRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.IDChatRecyclerView.adapter = adapter

        mChatViewModel = ViewModelProvider(this).get(ChatViewModel::class.java)
        mChatViewModel.chatData.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
            adapter.notifyDataSetChanged()
        })

    }

}