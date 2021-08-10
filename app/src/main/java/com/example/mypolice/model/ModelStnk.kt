package com.example.mypolice.model

class ModelStnk(val identitasKendaraan:IdentitasKendaraan,val rincianKendaraan:RincianKendaraan) {

}
class IdentitasKendaraan(val nomorPolisi:String,val jenisKendaraan:String,val merkKendaraan:String,val typeKendaraan:String,val tahunPembuatan:String,val warnaKendaraan:String,val tanggalMasaPKB:String,val tanggalMasaSTNK:String)
class RincianKendaraan(val pkb:Int,val sankiPKB:Int,val progressif:Int,val swdkllj:Int,sankiSwdkllj:Int,val pnbPengesahanSTNK:Int,parkirBerlangganan:Int,jumlahTotal:Int,)