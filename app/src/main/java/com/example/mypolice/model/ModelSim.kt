package com.example.mypolice.model

class ModelSim(val dataPermohonSim: DataPermohonSim,val dataDiriSim: DataDiriSim)
class DataPermohonSim(val jenisPermohonan:String,val golonganSim:String,val alamatEmail:String,val poldaKedatangan:String,val satpasKedatangan:String,val alamatSatpas:String)
class DataDiriSim(val kewarganegaraan:String,val nik:String,val namaLengkap:String,val golonganDarah:String,val kodePos:String,val kota:String,val alamat:String,val noHandphone:String,val pendidikan:String,val pekerjaan:String)