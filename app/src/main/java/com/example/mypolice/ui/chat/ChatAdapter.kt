package com.example.mypolice.ui.chat

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mypolice.databinding.ItemChatBinding
import com.example.mypolice.model.ModelChat
import java.net.URLEncoder

class ChatAdapter : RecyclerView.Adapter<ChatAdapter.ViewHolder>() {
    private var data = emptyList<ModelChat>()
    fun setData(datas: List<ModelChat>) {
        this.data = datas
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val datas = data[position]
        holder.binding.IDChatNamaPolres.text = datas.namaPolsek
        holder.data(datas)
    }

    override fun getItemCount(): Int {
        return data.size
    }


    class ViewHolder(val binding: ItemChatBinding) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var modelChat: ModelChat
        fun data(modelChats: ModelChat) {
            modelChat = modelChats
        }

        init {
            binding.IDChatWaPolres.setOnClickListener {
                val url =
                    "https://api.whatsapp.com/send?phone=" + modelChat.nomorHp + "&text=" + URLEncoder.encode(
                        "Permisi",
                        "UTF-8"
                    )
                val intent = Intent(Intent.ACTION_VIEW)
                intent.setData(Uri.parse(url))
                it.context.startActivity(intent)
            }
        }
    }
}