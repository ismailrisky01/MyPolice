package com.example.mypolice.ui.chat

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mypolice.model.ModelChat
import com.example.mypolice.utils.Repository

class ChatViewModel(application: Application) : AndroidViewModel(application) {
    val chatData: LiveData<List<ModelChat>>
    private val repository= Repository()

    init {
        chatData = repository.readChatData()

    }

}