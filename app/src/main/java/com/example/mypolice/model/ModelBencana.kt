package com.example.mypolice.model

class ModelBencana(val idLaporanBencan:String,val jenisBencana:String,val tanggalKejadian:String, val waktuKejadian:String,val kronologiBencana:String, val buktiBencana:String) {
    constructor():this("","","","","","")
}