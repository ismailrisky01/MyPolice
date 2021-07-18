package com.example.mypolice.model

class ModelBlankoSatu(val namaLengkap:String,val tempatLahir:String,val tanggalLahir:String, val agama:String,val kebangsaan:String,val jenisKelamin:String,val status:String, val pekerjaan:String, val alamat:String,val noKtp:String, val noKK:String, val noTelp:String) {
    constructor():this("","","","","","","","","","","","")
}