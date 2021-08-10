package com.example.mypolice.model

class ModelSensor(val idPengemudi:String,val latitude:Double,val longitude:Double,val namaAlat:String,namaPengemudi:String,val status:Boolean,val statusKecelakaan:String) {
    constructor():this("",0.0,0.0,"","",false,"")
}

class ModelSensorLapor(val idPengemudi:String,val namaAlat:String,namaPengemudi:String,val statusKecelakaan:String) {
    constructor():this("","","","")
}
class ModelSensorKu(val namaSensor:String) {
    constructor():this("")
}