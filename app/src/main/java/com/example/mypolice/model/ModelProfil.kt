package com.example.mypolice.model

class ModelProfil(val idUser:String, val nama :String, val ttl:String,val alamat:String,val noKTP:String,val noSIM:String,val noPolresTerdekat:String) {
    constructor():this("","","","","","","")
}