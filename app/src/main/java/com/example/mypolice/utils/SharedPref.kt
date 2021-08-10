package com.example.mypolice.utils

import android.content.Context
import com.example.mypolice.model.*

class SharedPref(context: Context) {
    var tinyDB = TinyDB(context)

    private val idSharedPrefUser = "User"
    private val idUidKey = "idUserKey"
    private val imageUserKey = "imageUserKey"
    private val namaUserKey = "namaUserKey"
    private val ttlUserKey = "ttlUserKey"
    private val alamatUserKey = "alamatUserKey"
    private val ktpUserKey = "ktpUserKey"
    private val simUserKey = "simUserKey"
    private val stnkUserKey = "stnkUserKey"

    private val sharedUser_id = context.getSharedPreferences(idSharedPrefUser, Context.MODE_PRIVATE)


    fun setDataUser(modelUser: ModelUser) {
        val data = sharedUser_id.edit()
        data.putString(idUidKey, modelUser.IdUser)
        data.putString(imageUserKey, modelUser.imageProfile)
        data.putString(namaUserKey, modelUser.nama)
        data.putString(ttlUserKey, modelUser.ttl)
        data.putString(alamatUserKey, modelUser.alamat)
        data.putString(ktpUserKey, modelUser.noKTP)
        data.putString(simUserKey, modelUser.noSIM)
        data.putString(stnkUserKey, modelUser.noRegistrasiSTNK)
        data.apply()
    }

    fun getDataUser(): ModelUser {
        val uidUser = sharedUser_id.getString(idUidKey, "").toString()
        val imageUser = sharedUser_id.getString(imageUserKey, "").toString()
        val namaUser = sharedUser_id.getString(namaUserKey, "").toString()
        val ttlUser = sharedUser_id.getString(ttlUserKey, "").toString()
        val alamatUser = sharedUser_id.getString(alamatUserKey, "").toString()
        val ktpUser = sharedUser_id.getString(ktpUserKey, "").toString()
        val simUser = sharedUser_id.getString(simUserKey, "").toString()
        val stnkUser = sharedUser_id.getString(stnkUserKey, "").toString()
        val data =
            ModelUser(uidUser, imageUser, namaUser, ttlUser, alamatUser, ktpUser, simUser, stnkUser)
        return data

    }

    private val idSharedPrefBlankoSatu = "Blankosatu"
    private val namaKey = "namaKey"
    private val tempatLahirKey = "tempatLahirKey"
    private val tanggalLahirKey = "tanggalLahirKey"
    private val agamaKey = "agamaKey"
    private val kebangsaanKey = "kebangsaanKey"
    private val kelaminKey = "kelaminKey"
    private val statusKey = "statusKey"
    private val pekerjaanKey = "pekerjaanKey"
    private val alamatKey = "alamatKey"
    private val ktpKey = "ktpKey"
    private val kkKey = "kkKey"
    private val telpKey = "telpKey"


    private val sharedBlankoSatu_id =
        context.getSharedPreferences(idSharedPrefBlankoSatu, Context.MODE_PRIVATE)

    fun setDataBlankoSatu(modelBlankoSatu: ModelBlankoSatu) {
        val data = sharedBlankoSatu_id.edit()
        data.putString(namaKey, modelBlankoSatu.namaLengkap)
        data.putString(tanggalLahirKey, modelBlankoSatu.tanggalLahir)
        data.putString(tempatLahirKey, modelBlankoSatu.tempatLahir)
        data.putString(agamaKey, modelBlankoSatu.agama)
        data.putString(kebangsaanKey, modelBlankoSatu.kebangsaan)
        data.putString(kelaminKey, modelBlankoSatu.jenisKelamin)
        data.putString(statusKey, modelBlankoSatu.status)
        data.putString(pekerjaanKey, modelBlankoSatu.pekerjaan)
        data.putString(alamatKey, modelBlankoSatu.alamat)
        data.putString(ktpKey, modelBlankoSatu.noKtp)
        data.putString(kkKey, modelBlankoSatu.noKK)
        data.putString(telpKey, modelBlankoSatu.noTelp)
        data.apply()
    }

    fun getDataBlankoSatu(): ModelBlankoSatu {
        val nama = sharedBlankoSatu_id.getString(namaKey, "").toString()
        val tempatLahir = sharedBlankoSatu_id.getString(tempatLahirKey, "").toString()
        val tanggalLahir = sharedBlankoSatu_id.getString(tanggalLahirKey, "").toString()
        val agama = sharedBlankoSatu_id.getString(agamaKey, "").toString()
        val kebangsaan = sharedBlankoSatu_id.getString(kebangsaanKey, "").toString()
        val kelamin = sharedBlankoSatu_id.getString(kelaminKey, "").toString()
        val status = sharedBlankoSatu_id.getString(statusKey, "").toString()
        val pekerjaan = sharedBlankoSatu_id.getString(pekerjaanKey, "").toString()
        val alamat = sharedBlankoSatu_id.getString(alamatKey, "").toString()
        val noktp = sharedBlankoSatu_id.getString(ktpKey, "").toString()
        val nokk = sharedBlankoSatu_id.getString(kkKey, "").toString()
        val notlp = sharedBlankoSatu_id.getString(telpKey, "").toString()

        val data = ModelBlankoSatu(
            nama,
            tempatLahir,
            tanggalLahir,
            agama,
            kebangsaan,
            kelamin,
            status,
            pekerjaan,
            alamat,
            noktp,
            nokk,
            notlp
        )
        return data
    }

    private val idSharedPrefBlankoDua = "BlankoDua"
    private val namaBapakKey = "namaBapakKey"
    private val tempatLahirBapakKey = "tempatLahirBapakKey"
    private val tanggalLahirBapakKey = "tanggalLahirBapakKey"
    private val agamaBapakKey = "agamaBapakKey"
    private val kebangsaanBapakKey = "kebangsaanBapakKey"
    private val statusBapakKey = "statusBapakKey"
    private val pekerjaanBapakKey = "pekerjaanBapakKey"
    private val alamatBapakKey = "alamatBapakKey"

    private val namaIbuKey = "namaIbuKey"
    private val tempatLahirIbuKey = "tempatLahirIbu"
    private val tanggalLahirIbuKey = "tanggalLahirIbu"
    private val agamaIbuKey = "agamaIbuKey"
    private val kebangsaanIbuKey = "kebangsaanIbuKey"
    private val statusIbuKey = "statusIbuKey"
    private val pekerjaanIbuKey = "pekerjaanIbuKey"
    private val alamatIbuKey = "alamatIbuKey"
    private val dataSaudaraKey = "dataSaudaraKey"
    private val sharedBlankoDua_id =
        context.getSharedPreferences(idSharedPrefBlankoDua, Context.MODE_PRIVATE)

    fun setDataBlankoDua(modelBlankoDua: ModelBlankoDua) {
        tinyDB.putString(namaBapakKey, modelBlankoDua.namaBapak)
        tinyDB.putString(tempatLahirBapakKey, modelBlankoDua.tempatLahirAyah)
        tinyDB.putString(tanggalLahirBapakKey, modelBlankoDua.tanggalLahirAyah)
        tinyDB.putString(agamaBapakKey, modelBlankoDua.agamaBapak)
        tinyDB.putString(kebangsaanBapakKey, modelBlankoDua.kebangsaanBapak)
        tinyDB.putString(statusBapakKey, modelBlankoDua.statusBapak)
        tinyDB.putString(pekerjaanBapakKey, modelBlankoDua.pekerjaanBapak)
        tinyDB.putString(alamatBapakKey, modelBlankoDua.alamatBapak)

        tinyDB.putString(namaIbuKey, modelBlankoDua.namaIbu)
        tinyDB.putString(tempatLahirIbuKey, modelBlankoDua.tempatLahirIbu)
        tinyDB.putString(tanggalLahirIbuKey, modelBlankoDua.tanggalLahirIbu)
        tinyDB.putString(agamaIbuKey, modelBlankoDua.agamaIbu)
        tinyDB.putString(kebangsaanIbuKey, modelBlankoDua.kebangsaanIbu)
        tinyDB.putString(statusIbuKey, modelBlankoDua.statusIbu)
        tinyDB.putString(pekerjaanIbuKey, modelBlankoDua.pekerjaanIbu)
        tinyDB.putString(alamatIbuKey, modelBlankoDua.alamatIbu)
        tinyDB.putListObject(dataSaudaraKey, modelBlankoDua.dataSaudara)
        tinyDB.apply { }

    }

    fun getDataBlankoDua(): ModelBlankoDua {
        val namaBapak = tinyDB.getString(namaBapakKey) as String
        val tempatLahirBapak = tinyDB.getString(tempatLahirBapakKey) as String
        val tanggalLahirBapak = tinyDB.getString(tanggalLahirBapakKey) as String
        val agamaBapak = tinyDB.getString(agamaBapakKey) as String
        val kebangsaanBapak = tinyDB.getString(kebangsaanBapakKey) as String
        val statusBapak = tinyDB.getString(statusBapakKey) as String
        val pekerjaanBapak = tinyDB.getString(pekerjaanBapakKey) as String
        val alamatBapak = tinyDB.getString(alamatBapakKey) as String
        val namaIbu = tinyDB.getString(namaIbuKey) as String
        val tempatLahirIbu = tinyDB.getString(tempatLahirIbuKey) as String
        val tanggalLahirIbu = tinyDB.getString(tanggalLahirIbuKey) as String
        val agamaIbu = tinyDB.getString(agamaIbuKey) as String
        val kebangsaanIbu = tinyDB.getString(kebangsaanIbuKey) as String
        val statusIbu = tinyDB.getString(statusIbuKey) as String
        val pekerjaanIbu = tinyDB.getString(pekerjaanIbuKey) as String
        val alamatIbu = tinyDB.getString(alamatIbuKey) as String
        val dataSaudara = tinyDB.getListObject(dataSaudaraKey)

        return ModelBlankoDua(
            namaBapak,
            tempatLahirBapak,
            tanggalLahirBapak,
            agamaBapak,
            kebangsaanBapak,
            statusBapak,
            pekerjaanBapak,
            alamatBapak,
            namaIbu,
            tempatLahirIbu,
            tanggalLahirIbu,
            agamaIbu,
            kebangsaanIbu,
            statusIbu,
            pekerjaanIbu,
            alamatIbu,
            dataSaudara
        )
    }


    private val idSharedPrefBlankoTiga = "BlankoTiga"
    private val namaSDKey = "namaSDKey"
    private val kotaSDKey = "kotaSDKey"
    private val tahunSDKey = "tahunSDKey"
    private val namaSMPKey = "namaSMPKey"
    private val kotaSMPKey = "kotaSMPKey"
    private val tahunSMPKey = "tahunSMPKey"
    private val namaSMAKey = "namaSMAKey"
    private val kotaSMAKey = "kotaSMAKey"
    private val tahunSMAKey = "tahunSMAKey"
    private val namaUNIVKey = "namaUNIVKey"
    private val kotaUNIVKey = "kotaUNIVKey"
    private val tahunUNIVKey = "tahunUNIVKey"
    private val sharedBlankoTiga_id =
        context.getSharedPreferences(idSharedPrefBlankoTiga, Context.MODE_PRIVATE)

    fun setDataBlankoTiga(modelBlankoTiga: ModelBlankoTiga) {
        val data = sharedBlankoTiga_id.edit()
        tinyDB.putString(namaSDKey, modelBlankoTiga.namaSD)
        tinyDB.putString(kotaSDKey, modelBlankoTiga.kotaSD)
        tinyDB.putString(tahunSDKey, modelBlankoTiga.tahunSD)
        tinyDB.putString(namaSMPKey, modelBlankoTiga.namaSMP)
        tinyDB.putString(kotaSMPKey, modelBlankoTiga.kotaSMP)
        tinyDB.putString(tahunSMPKey, modelBlankoTiga.tahunSMP)
        tinyDB.putString(namaSMAKey, modelBlankoTiga.namaSMA)
        tinyDB.putString(kotaSMAKey, modelBlankoTiga.kotaSMA)
        tinyDB.putString(tahunSMAKey, modelBlankoTiga.tahunSMA)
        tinyDB.putString(namaUNIVKey, modelBlankoTiga.namaUNIV)
        tinyDB.putString(kotaUNIVKey, modelBlankoTiga.kotaUNIV)
        tinyDB.putString(tahunUNIVKey, modelBlankoTiga.tahunUNIV)
        tinyDB.apply { }


    }

    fun getDataBlankoTiga(): ModelBlankoTiga {
        val namaSD = tinyDB.getString(namaSDKey).toString()
        val kotaSD = tinyDB.getString(kotaSDKey).toString()
        val tahunSD = tinyDB.getString(tahunSDKey).toString()
        val namaSMP = tinyDB.getString(namaSMPKey).toString()
        val kotaSMP = tinyDB.getString(kotaSMPKey).toString()
        val tahunSMP = tinyDB.getString(tahunSMPKey).toString()
        val namaSMA = tinyDB.getString(namaSMAKey).toString()
        val kotaSMA = tinyDB.getString(kotaSMAKey).toString()
        val tahunSMA = tinyDB.getString(tahunSMAKey).toString()
        val namaUNIV = tinyDB.getString(namaUNIVKey).toString()
        val kotaUNIV = tinyDB.getString(kotaUNIVKey).toString()
        val tahunUNIV = tinyDB.getString(tahunUNIVKey).toString()

        val data = ModelBlankoTiga(
            namaSD,
            kotaSD,
            tahunSD,
            namaSMP,
            kotaSMP,
            tahunSMP,
            namaSMA,
            kotaSMA,
            tahunSMA,
            namaUNIV,
            kotaUNIV,
            tahunUNIV
        )
        return data
    }

    private val jenisPemohonKey = "jenisPemohonKey"
    private val golonganSimKey = "golonganSimKey"
    private val alamatEmailSIMKey = "alamatEmailSIMKey"
    private val poldaKedatanganKey = "poldaKedatangan"
    private val satpasKedatanganKey = "satpasKedatangan"
    private val alamatSatpasKedatanganKey = "alamatSatpasKedatangan"

    fun setDataSimDataPemohon(dataPermohonSim: DataPermohonSim) {
        tinyDB.putString(jenisPemohonKey, dataPermohonSim.jenisPermohonan)
        tinyDB.putString(golonganSimKey, dataPermohonSim.golonganSim)
        tinyDB.putString(alamatEmailSIMKey, dataPermohonSim.alamatEmail)
        tinyDB.putString(poldaKedatanganKey, dataPermohonSim.poldaKedatangan)
        tinyDB.putString(satpasKedatanganKey, dataPermohonSim.satpasKedatangan)
        tinyDB.putString(alamatSatpasKedatanganKey, dataPermohonSim.alamatSatpas)
    }

    fun getDataSimDataPemohon(): DataPermohonSim {
        val jenisPemohon = tinyDB.getString(jenisPemohonKey) as String
        val golonganSim = tinyDB.getString(golonganSimKey) as String
        val alamatEmail = tinyDB.getString(alamatEmailSIMKey) as String
        val poldaKedatangan = tinyDB.getString(poldaKedatanganKey) as String
        val satpasKedatangan = tinyDB.getString(satpasKedatanganKey) as String
        val alamatSatpas = tinyDB.getString(alamatSatpasKedatanganKey) as String
        return DataPermohonSim(
            jenisPemohon,
            golonganSim,
            alamatEmail,
            poldaKedatangan,
            satpasKedatangan,
            alamatSatpas
        )
    }

    private val kewarganegaraanSIMKey = "kewarganegaraanSIMKey"
    private val nikSIMKey="nikSIMKey"
    private val namaLengkapSIMKey = "namaLengkapSIMKey"
    private val golonganDarahSIMKey = "golonganDarahSIMKey"
    private val kodePosSIMKey = "kodePosSIMKey"
    private val kotaSIMKey = "kotaSIMKey"
    private val alamatSIMKey="alamatSIMKey"
    private val noHandphoneSIMKey="noHandphoneSIMKey"
    private val pendidikanSIMKey="pendidikanSIMKey"
    private val pekerjaanSIMKey="pekerjaanSIMKey"

fun setDataPribadi(dataDiriSim: DataDiriSim){
    tinyDB.putString(kewarganegaraanSIMKey,dataDiriSim.kewarganegaraan)
    tinyDB.putString(nikSIMKey,dataDiriSim.nik)
    tinyDB.putString(namaLengkapSIMKey,dataDiriSim.namaLengkap)
    tinyDB.putString(golonganDarahSIMKey,dataDiriSim.golonganDarah)
    tinyDB.putString(kodePosSIMKey,dataDiriSim.kodePos)
    tinyDB.putString(kotaSIMKey,dataDiriSim.kota)
    tinyDB.putString(alamatSIMKey,dataDiriSim.alamat)
    tinyDB.putString(noHandphoneSIMKey,dataDiriSim.noHandphone)
    tinyDB.putString(pendidikanSIMKey,dataDiriSim.pendidikan)
    tinyDB.putString(pekerjaanSIMKey,dataDiriSim.pekerjaan)
}
    fun getDataPribadi():DataDiriSim{
        val kewarganegaraan = tinyDB.getString(kewarganegaraanSIMKey) as String
        val nik = tinyDB.getString(nikSIMKey)as String
        val namaLengkap = tinyDB.getString(namaLengkapSIMKey)as String
        val golonganDarah = tinyDB.getString(golonganDarahSIMKey)as String
        val kodePos = tinyDB.getString(kodePosSIMKey)as String
        val kota = tinyDB.getString(kotaSIMKey)as String
        val alamat = tinyDB.getString(alamatSIMKey)as String
        val noHandphone = tinyDB.getString(noHandphoneSIMKey)as String
        val pendidikan = tinyDB.getString(pendidikanSIMKey)as String
        val pekerjaan = tinyDB.getString(pekerjaanSIMKey)as String
        return DataDiriSim(kewarganegaraan,nik,namaLengkap,golonganDarah,kodePos,kota,alamat,noHandphone,pendidikan,pekerjaan)
    }
}