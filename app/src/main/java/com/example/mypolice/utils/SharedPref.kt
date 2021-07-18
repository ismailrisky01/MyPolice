package com.example.mypolice.utils

import android.content.Context
import com.example.mypolice.model.ModelBlankoDua
import com.example.mypolice.model.ModelBlankoSatu
import com.example.mypolice.model.ModelBlankoTiga
import com.example.mypolice.model.ModelUser

class SharedPref(context: Context) {

    private  val idSharedPrefUser = "User"
    private val idUidKey = "idUserKey"
    private val imageUserKey ="imageUserKey"
    private val namaUserKey = "namaUserKey"
    private val ttlUserKey = "ttlUserKey"
    private val alamatUserKey = "alamatUserKey"
    private val ktpUserKey = "ktpUserKey"
    private val simUserKey = "simUserKey"
    private val stnkUserKey = "stnkUserKey"

    private val sharedUser_id = context.getSharedPreferences(idSharedPrefUser, Context.MODE_PRIVATE)


    fun setDataUser(modelUser: ModelUser){
        val data = sharedUser_id.edit()
        data.putString(idUidKey,modelUser.IdUser)
        data.putString(imageUserKey,modelUser.imageProfile)
        data.putString(namaUserKey,modelUser.nama)
        data.putString(ttlUserKey,modelUser.ttl)
        data.putString(alamatUserKey,modelUser.alamat)
        data.putString(ktpUserKey,modelUser.noKTP)
        data.putString(simUserKey,modelUser.noSIM)
        data.putString(stnkUserKey,modelUser.noRegistrasiSTNK)
        data.apply()
    }

    fun getDataUser():ModelUser{
        val uidUser = sharedUser_id.getString(idUidKey, "").toString()
        val imageUser = sharedUser_id.getString(imageUserKey, "").toString()
        val namaUser = sharedUser_id.getString(namaUserKey, "").toString()
        val ttlUser = sharedUser_id.getString(ttlUserKey, "").toString()
        val alamatUser = sharedUser_id.getString(alamatUserKey, "").toString()
        val ktpUser = sharedUser_id.getString(ktpUserKey, "").toString()
        val simUser = sharedUser_id.getString(simUserKey, "").toString()
        val stnkUser = sharedUser_id.getString(stnkUserKey, "").toString()
        val data = ModelUser(uidUser,imageUser,namaUser,ttlUser,alamatUser,ktpUser,simUser,stnkUser)
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
    private val sharedBlankoDua_id =
        context.getSharedPreferences(idSharedPrefBlankoDua, Context.MODE_PRIVATE)

    fun setDataBlankoDua(modelBlankoDua: ModelBlankoDua) {
        val data = sharedBlankoDua_id.edit()
        data.putString(namaBapakKey, modelBlankoDua.namaBapak)
        data.putString(tempatLahirBapakKey, modelBlankoDua.tempatLahirAyah)
        data.putString(tanggalLahirBapakKey, modelBlankoDua.tanggalLahirAyah)
        data.putString(agamaBapakKey, modelBlankoDua.agamaBapak)
        data.putString(kebangsaanBapakKey, modelBlankoDua.kebangsaanBapak)
        data.putString(statusBapakKey, modelBlankoDua.statusBapak)
        data.putString(pekerjaanBapakKey, modelBlankoDua.pekerjaanBapak)
        data.putString(alamatBapakKey, modelBlankoDua.alamatBapak)

        data.putString(namaIbuKey, modelBlankoDua.namaIbu)
        data.putString(tempatLahirIbuKey, modelBlankoDua.tempatLahirIbu)
        data.putString(tanggalLahirIbuKey, modelBlankoDua.tanggalLahirIbu)
        data.putString(agamaIbuKey, modelBlankoDua.agamaIbu)
        data.putString(kebangsaanIbuKey, modelBlankoDua.kebangsaanIbu)
        data.putString(statusIbuKey, modelBlankoDua.statusIbu)
        data.putString(pekerjaanIbuKey, modelBlankoDua.pekerjaanIbu)
        data.putString(alamatIbuKey, modelBlankoDua.alamatIbu)
        data.apply()
    }

    fun getDataBlankoDua(): ModelBlankoDua {
        val namaBapak = sharedBlankoDua_id.getString(namaBapakKey, "").toString()
        val tempatLahirBapak = sharedBlankoDua_id.getString(tempatLahirBapakKey, "").toString()
        val tanggalLahirBapak = sharedBlankoDua_id.getString(tanggalLahirBapakKey, "").toString()
        val agamaBapak = sharedBlankoDua_id.getString(agamaBapakKey, "").toString()
        val kebangsaanBapak = sharedBlankoDua_id.getString(kebangsaanBapakKey, "").toString()
        val statusBapak = sharedBlankoDua_id.getString(statusBapakKey, "").toString()
        val pekerjaanBapak = sharedBlankoDua_id.getString(pekerjaanBapakKey, "").toString()
        val alamatBapak = sharedBlankoDua_id.getString(alamatBapakKey, "").toString()
        val namaIbu = sharedBlankoDua_id.getString(namaIbuKey, "").toString()
        val tempatLahirIbu = sharedBlankoDua_id.getString(tempatLahirIbuKey, "").toString()
        val tanggalLahirIbu = sharedBlankoDua_id.getString(tanggalLahirIbuKey, "").toString()
        val agamaIbu = sharedBlankoDua_id.getString(agamaIbuKey, "").toString()
        val kebangsaanIbu = sharedBlankoDua_id.getString(kebangsaanIbuKey, "").toString()
        val statusIbu = sharedBlankoDua_id.getString(statusIbuKey, "").toString()
        val pekerjaanIbu = sharedBlankoDua_id.getString(pekerjaanIbuKey, "").toString()
        val alamatIbu = sharedBlankoDua_id.getString(alamatIbuKey, "").toString()

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
            alamatIbu
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
        data.putString(namaSDKey, modelBlankoTiga.namaSD)
        data.putString(kotaSDKey, modelBlankoTiga.namaSD)
        data.putString(tahunSDKey, modelBlankoTiga.namaSD)
        data.putString(namaSMPKey, modelBlankoTiga.namaSD)
        data.putString(kotaSMPKey, modelBlankoTiga.namaSD)
        data.putString(tahunSMPKey, modelBlankoTiga.namaSD)
        data.putString(namaSMAKey, modelBlankoTiga.namaSD)
        data.putString(kotaSMAKey, modelBlankoTiga.namaSD)
        data.putString(tahunSMAKey, modelBlankoTiga.namaSD)
        data.putString(namaUNIVKey, modelBlankoTiga.namaSD)
        data.putString(kotaUNIVKey, modelBlankoTiga.namaSD)
        data.putString(tahunUNIVKey, modelBlankoTiga.namaSD)
        data.apply()


    }

    fun getDataBlankoTiga(): ModelBlankoTiga {
        val namaSD = sharedBlankoTiga_id.getString(namaSDKey, "").toString()
        val kotaSD = sharedBlankoTiga_id.getString(namaSDKey, "").toString()
        val tahunSD = sharedBlankoTiga_id.getString(namaSDKey, "").toString()
        val namaSMP = sharedBlankoTiga_id.getString(namaSDKey, "").toString()
        val kotaSMP = sharedBlankoTiga_id.getString(namaSDKey, "").toString()
        val tahunSMP = sharedBlankoTiga_id.getString(namaSDKey, "").toString()
        val namaSMA = sharedBlankoTiga_id.getString(namaSDKey, "").toString()
        val kotaSMA = sharedBlankoTiga_id.getString(namaSDKey, "").toString()
        val tahunSMA = sharedBlankoTiga_id.getString(namaSDKey, "").toString()
        val namaUNIV = sharedBlankoTiga_id.getString(namaSDKey, "").toString()
        val kotaUNIV = sharedBlankoTiga_id.getString(namaSDKey, "").toString()
        val tahunUNIV = sharedBlankoTiga_id.getString(namaSDKey, "").toString()

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


}