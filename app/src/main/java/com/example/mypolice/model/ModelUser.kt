package com.example.mypolice.model

class ModelUser(val IdUser:String,val imageProfile:String,val nama:String,val ttl:String, val alamat:String,val noKTP:String,val noSIM:String,val noRegistrasiSTNK:String) {
    constructor():this("","","","","","","","")
}