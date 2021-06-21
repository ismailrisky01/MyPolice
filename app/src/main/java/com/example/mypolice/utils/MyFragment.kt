package com.example.mypolice.utils

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.mypolice.R

open class MyFragment<T : ViewDataBinding>(private val layout: Int) : Fragment() {

    val loading by lazy { LoadingHelper(requireContext()) }

    lateinit var binding: T
        private set

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layout, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

}


class LoadingHelper(context: Context) {

    private val dialog = Dialog(context).apply {
        setContentView(R.layout.layout_loading) // TODO("Should Be Change to ViewBinding Inflation")
        setCancelable(false)
    }

    fun show() = show(null, null)

    fun show(title: String) = show(title, null)

    fun show(title: String?, desc: String?) {
        dialog.show()
    }

    fun dismiss() {
        dialog.dismiss()
    }

}