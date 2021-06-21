package com.example.mypolice.model

class ModelLaporan(val idLaporan:String,val tanggalLaporan:String,val identitasPelapor:String, val waktuKejadian:String,val tempatKejadian:String,val apaYangterjadi:String, val terlapor:String,val korban:String, val bagaimanaTerjadi:String,val saksiSaksi:String,val identitasSaksi1:String, val identitasSaksi2:String,val uraianSingkat: String ) {
    constructor():this("","","","","","","","","","","","","")
}