package com.example.mypolice.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mypolice.model.*
import com.example.mypolice.ui.dashboard.halo_polisi.pemadam.PemadamAdapter
import com.example.mypolice.utils.retrofit.Network
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
//import es.dmoral.toasty.Toasty
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class Repository {
    private val uid = FirebaseAuth.getInstance().currentUser?.uid
    private val firebaserealtime = FirebaseDatabase.getInstance().reference.child("MyPolice")
    val firestore = FirebaseFirestore.getInstance()

    init {
        readChatData()
    }

    fun login() {

    }

    fun readChatData(): LiveData<List<ModelChat>> {
        val ref = firebaserealtime.child("datachatpolisis")
        val mutableList = MutableLiveData<List<ModelChat>>()
        ref.addValueEventListener(object : ValueEventListener {
            val data = mutableListOf<ModelChat>()
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach {
                    val dataSnapshot = it.getValue(ModelChat::class.java) as ModelChat
                    data.add(dataSnapshot)
                }
                mutableList.value = data
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })

        return mutableList
    }

//TrackRecord

    fun readTrackData(): LiveData<MutableList<ModelTrackRecord>> {
        val sdf = SimpleDateFormat("dd-M-yyyy")
        val time = sdf.format(Date())
//        firestore.collection("TrackRecord").document().set(ModelTrackRecord("Dukung PPKM Darurat, Kapolri Minta Jajaran \n" +
//                "Siapkan Strategi Penyekatan","https://firebasestorage.googleapis.com/v0/b/database2-d6a2c.appspot.com/o/MyPolice%2FRectangle%20230.png?alt=media&token=8933017a-94e4-4020-b736-28f16410d808", "Jakarta - Pemerintah resmi mengumumkan penerapan PPKM darurat bakal dilaksanakan pada 3-20 Juli 2021. Kapolri Jenderal Listyo Sigit Prabowo meminta jajarannya agar mempersiapkan segala strategi untuk mendukung pelaksanaan PPKM darurat.\n" +
//                "\"Besok kita akan memasuki masa PPKM Darurat dan Operasi Aman Nusa II Penanganan COVID-19 Lanjutan di Jawa dan Bali. Persiapkan segera strategi penjagaan dan penyekatan, pendisiplinan protokol kesehatan, dan implementasi lapangan kebijakan pembatasan ini,\" ujar Sigit melalui keterangan tertulis, Kamis (1/7/2021).\n" +
//                "Sigit menjelaskan, Polri memiliki anggota yang tangguh. Dengan demikian, Sigit memastikan pihaknya bakal mengerahkan seluruh kekuatan untuk membantu penanganan pandemi COVID-19.\n" +
//                "\n" +
//                "\"Sebagai Bhayangkara yang tangguh, Polri juga harus melakukan upaya terbaik dengan mengerahkan seluruh sumber daya yang ada dalam rangka membantu penanganan pandemi COVID-19,\" tuturnya.\n" +
//                "\n" +
//                "Lebih lanjut, Sigit membeberkan ada beberapa cara yang bisa dilakukan kepolisian untuk membantu penanganan COVID-19. Di antaranya seperti melakukan vaksinasi hingga penyekatan jalan untuk membatasi mobilitas warga.\n" +
//                "\n" +
//                "\"Melalui pendisiplinan protokol kesehatan 5M, penguatan upaya 3T, penjagaan dan penyekatan, operasi yustisi, dan membantu akselerasi program vaksinasi nasional melalui gerai vaksinasi Presisi dan vaksinasi massal,\" imbuh Sigit.\n" +
//                "\n" +
//                "Sebelumnya, Presiden Joko Widodo (Jokowi) memberlakukan PPKM darurat di Jawa dan Bali dimulai sejak 3 Juli hingga 20 Juli 2021. Jokowi mengatakan akan mengerahkan seluruh sumber daya untuk membantu mengatasi penyebaran COVID-19.\n" +
//                "\n" +
//                "\"Pemerintah akan mengerahkan seluruh sumber daya yang ada untuk mengatasi penyebaran COVID, seluruh aparat negara TNI, Polri maupun aparatur sipil negara, dokter, dan tenaga kesehatan harus bahu-membahu bekerja sebaik-baiknya untuk menangani wabah ini,\" ujar Jokowi saat konferensi pers yang disiarkan langsung di YouTube Setpres, Kamis (1/7). ",time))
        val mutableList = MutableLiveData<MutableList<ModelTrackRecord>>()

        FirebaseFirestore.getInstance().collection("TrackRecord").get()
            .addOnSuccessListener { result ->
                val data = mutableListOf<ModelTrackRecord>()
                result.forEach { document ->
                    val it = document.toObject(ModelTrackRecord::class.java)
                    data.add(it)
                }
                mutableList.value = data

            }


        return mutableList
    }
    //EndTrackRecord

    fun getInfoTilang(nomorTilang: String): ModelTilang {

        return ModelTilang(nomorTilang, 20000)

    }

    //Profile
    fun getProfileData(context: Context): LiveData<ModelUser> {
        val loading = LoadingHelper(context)
        loading.show()
        val mutableList = MutableLiveData<ModelUser>()

        val ref = FirebaseFirestore.getInstance().collection("MyPolice")
            .document("UserAccount/$uid/DataDiri")
        ref.get().addOnSuccessListener { document ->

            if (document.exists()) {
                loading.dismiss()
                val data = document.toObject(ModelUser::class.java) as ModelUser
                mutableList.value = data
            } else {
                loading.dismiss()
mutableList.value  = ModelUser("Harap Isi","","","","","","","")
            }
        }
        return mutableList
    }

    fun updateProfile(modelUser: ModelUser, context: Context) {
        val loading = LoadingHelper(context)
        val myprefrence = SharedPref(context)
        loading.show()
        myprefrence.setDataUser(modelUser)

        FirebaseFirestore.getInstance().collection("MyPolice")
            .document("UserAccount/${FirebaseAuth.getInstance().currentUser?.uid}/DataDiri")
            .set(modelUser).addOnSuccessListener {
                logD("Succes Update Profile")
//                Toasty.success(context, "Success to update", Toast.LENGTH_SHORT, true).show()
                loading.dismiss()
            }.addOnFailureListener {
                logD("Error" + it.message)
//                Toasty.error(context, "Failed to update", Toast.LENGTH_SHORT, true).show()
                loading.dismiss()

            }
    }
    //End Profile

    //Laporan
    fun uploadLaporan(modelLaporan: ModelLaporan, context: Context) {
        val loading = LoadingHelper(context)
        loading.show()
        val ref =
            FirebaseFirestore.getInstance().collection("MyPolice/Laporan/DataLaporan").document()
        ref.set(
            ModelLaporan(
                ref.id,
                modelLaporan.ktpPelapor,
                modelLaporan.tanggalLaporan,
                modelLaporan.identitasPelapor,
                modelLaporan.waktuKejadian,
                modelLaporan.tempatKejadian,
                modelLaporan.apaYangterjadi,
                modelLaporan.terlapor,
                modelLaporan.korban,
                modelLaporan.bagaimanaTerjadi,
                modelLaporan.saksiSaksi,
                modelLaporan.identitasSaksi1,
                modelLaporan.identitasSaksi2,
                modelLaporan.uraianSingkat
            )
        ).addOnSuccessListener {
            loading.dismiss()
        }
    }

    fun uploadLaporanKejadian(
        modelLaporanKejadian: ModelLaporanKejadian,
        context: Context,
        kategori: String
    ) {

        val loading = LoadingHelper(context)
        loading.show()
        val ref =
            FirebaseFirestore.getInstance().collection("MyPolice/Laporan/DataLaporan").document()
        val data = ModelLaporanKejadian(
            ref.id,
            modelLaporanKejadian.identiasPelapor,
            kategori,
            modelLaporanKejadian.alamatKejadian,
            modelLaporanKejadian.tanggalKejadian,
            modelLaporanKejadian.desktripsiKejadian
        )
        ref.set(data).addOnSuccessListener {
            loading.dismiss()
//            Toasty.success(context, "Success!", Toast.LENGTH_SHORT, true).show()
            loading.dismiss()
        }.addOnFailureListener {
            loading.dismiss()
//            Toasty.error(context, "This is an error toast.", Toast.LENGTH_SHORT, true).show()
        }.addOnCompleteListener {
            loading.dismiss()

        }
    }

    //EndLaporan

    //Bencana
    fun postImageBencana(imageUri: Uri): MutableLiveData<String> {
        logD("UploadImageBencana")
        val filename = UUID.randomUUID().toString()
        val storage = FirebaseStorage.getInstance().getReference("/MyPolice/$filename")
        val uri = MutableLiveData<String>()
        storage.putFile(imageUri).addOnSuccessListener {
            storage.downloadUrl.addOnSuccessListener {
                logD("Disimpan ke $it")
                uri.value = it.toString()
            }
        }
        return uri
    }

    fun postDataBenca(modelBencana: ModelBencana, context: Context) {
        logD("uploadDataBencana")
        val loading = LoadingHelper(context)
        loading.show()
        val ref =
            FirebaseFirestore.getInstance().collection("MyPolice/Laporan/DataBencana").document()
        val data = ModelBencana(
            ref.id,
            modelBencana.jenisBencana,
            modelBencana.tanggalKejadian,
            modelBencana.waktuKejadian,
            modelBencana.kronologiBencana,
            modelBencana.buktiBencana
        )
        ref.set(data).addOnSuccessListener {
            loading.dismiss()

        }
    }

    fun getMapsData(context: Context,loc: LatLng, lokasi: String, name: String): LiveData<MutableList<Result>> {
        val mutableList = MutableLiveData<MutableList<Result>>()
        val loading = LoadingHelper(context)
        loading.show()
        Network().getServiceData().getData(
            loc.latitude.toString() + "," + loc.longitude.toString(),
            "20000",
            lokasi, name,
            "AIzaSyAt6rC_iziBuu0k3tscBmPp--H3kC7qwas"
        )
            .enqueue(object : Callback<ModelRoot> {
                val data = mutableListOf<Result>()

                override fun onResponse(call: Call<ModelRoot>, response: Response<ModelRoot>) {
                    logD("Retrofit")
                    response.body()?.results?.forEach {
                        data.add(it)
                    }
                    mutableList.value = data
                    loading.dismiss()
                }

                override fun onFailure(call: Call<ModelRoot>, t: Throwable) {
                    logD(t.message + t.localizedMessage)
                    loading.dismiss()
                }
            })
        return mutableList
    }

    fun getMyLocation(context: Context,activity:Activity):LatLng{
        val mFusedLocation = LocationServices.getFusedLocationProviderClient(context)
        val adapter = PemadamAdapter()
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
        }
        mFusedLocation.lastLocation.addOnSuccessListener(activity, object :
            OnSuccessListener<Location> {
            override fun onSuccess(location: Location?) {
                // Do it all with location
                Log.d("My Current location", "Lat : ${location?.latitude} Long : ${location?.longitude}")
            }

        })
        return LatLng(1.0,1.0)
    }
}