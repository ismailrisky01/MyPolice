package com.example.mypolice.utils

import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
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
class CallHelper(context: Context) {

    private val dialog = Dialog(context).apply {
        setContentView(R.layout.call_load) // TODO("Should Be Change to ViewBinding Inflation")
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
class Dialog(context: Context){
    private val builder = AlertDialog.Builder(context)
    val positiveButtonClick = { dialog: DialogInterface, which: Int ->

    }
    fun show(){
        with(builder)
        {
            setTitle("Kebijakan Privasi")
            setMessage(" - PEMBUAT LAPORAN PALSU -\n" +
                    "Pasal 220 KUHP Barang siapa memberitahukan atau  mengadukan bahwa telah dilakukan \n" +
                    "suatu perbuatan pidana, padahal mengetahui bahwa itu tidak dilakukan, diancam \n" +
                    "dengan pidana penjara  ")
            setPositiveButton("Mengerti", DialogInterface.OnClickListener(function = positiveButtonClick))
            show()
        }
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
fun backStack(view: View,context: Context){
    val callback: OnBackPressedCallback =
        object : OnBackPressedCallback(true /* enabled by default */) {
            override fun handleOnBackPressed() {
                // Handle the back button even
                view.findNavController().popBackStack()
            }
        }
}