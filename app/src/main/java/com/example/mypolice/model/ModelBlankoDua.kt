package com.example.mypolice.model

import java.util.ArrayList

class ModelBlankoDua(
    val namaBapak:String,
    val tempatLahirAyah:String, val tanggalLahirAyah:String,
    val agamaBapak:String,
    val kebangsaanBapak:String,
    val statusBapak:String,
    val pekerjaanBapak:String,
    val alamatBapak:String,
    val namaIbu:String,
    val tempatLahirIbu:String,
    val tanggalLahirIbu:String,
    val agamaIbu:String,
    val kebangsaanIbu:String,
    val statusIbu:String,
    val pekerjaanIbu:String,
    val alamatIbu:String,
    val dataSaudara: ArrayList<ModelSaudara>
) {
}
class ModelSaudara(val namaSaudara:String, val tempatLahirSaudara:String,val tanggalLahirSaudara: String,val pekerjaanSaudara:String,val alamatSaudara:String)