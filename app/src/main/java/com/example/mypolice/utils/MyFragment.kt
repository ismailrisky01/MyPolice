package com.example.mypolice.utils

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.mypolice.R
import java.text.SimpleDateFormat
import java.util.*

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
class DatePickerMe(){

    var cal = Calendar.getInstance()
    val dateSetListener = object : DatePickerDialog.OnDateSetListener {
        override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                               dayOfMonth: Int) {
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)


        }
    }
    fun getDatePicker(context: Context):String{
        DatePickerDialog(context,
            dateSetListener,
            // set DatePickerDialog to point to today's date when it loads up
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)).show()
        val myFormat = "MM/dd/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        return sdf.format(cal.getTime())
    }



}

fun logD(message: Any?) {
    Log.d("MyPolice", "Message : ${message ?: "Null"}")
}