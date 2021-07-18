package com.example.mypolice.model

class ModelLaporanKejadian(val idLaporan:String, val identiasPelapor:String,val kategoriKejadian:String, val alamatKejadian:String, val tanggalKejadian:String, val desktripsiKejadian:String) {
    constructor():this("","","","","","")
}