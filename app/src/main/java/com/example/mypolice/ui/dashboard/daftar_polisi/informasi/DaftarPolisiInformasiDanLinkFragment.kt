package com.example.mypolice.ui.dashboard.daftar_polisi.informasi

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mypolice.R
import com.example.mypolice.databinding.FragmentDaftarPolisiInformasiDanLinkBinding
import com.example.mypolice.model.ModelInformasi
import com.example.mypolice.utils.MyFragment


class DaftarPolisiInformasiDanLinkFragment :
    MyFragment<FragmentDaftarPolisiInformasiDanLinkBinding>(
        R.layout.fragment_daftar_polisi_informasi_dan_link
    ) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = DaftarPolisiAdapter()
        val data = ArrayList<ModelInformasi>()
        data.add(
            ModelInformasi(
                "1",
                "Pendaftaran online SIPSS Polisi",
                "SIPSS merupakan salah satu singkatan dari Sekolah Inspektur Polisi Sumber Sarjana dikhususkan untuk sarjana dalam katagori program studi yang dibutukan saja malai dari D IV, S1 sampai dengan S2 untuk pendaftaran dilaksanakan melalui online pada link http://penerimaan.polri.go.id seleksi ini menjadi yang pertama dibuka dari semua jenis seleksi yang ada pada rekrutmen Polsi"
            )
        )
        data.add(
            ModelInformasi(
                "15",
                "Pendaftaran online AKPOL Polisi",
                "AKPOL merupakan singkatan dari Akademi Kepolisian yang akan dilaksanakan melalui Polda Asal dimana mereka yang ingin mendaftar seleksi ini harus berdomisili pada suatu provinsi lebih dari 2 tahun lamanya selain itu Penerimaan AKPOL hanya akan dilaksanakan melalui Polda. Selain itu pendaftaran AKPL dilaksanakan melalui online seperti pada link berikut ini http://penerimaan.polri.go.id/form_registrasi/1 selanjutnya anda bisa mengisi semua formulir pendaftaran sesuai dengan identitas anda. Bisa diikuti oleh lulusan SMA/SMK/MA sederajat."
            )
        )
        data.add(
            ModelInformasi(
                "221",
                "Pendaftaran online BINTARA Polisi",
                "BINTARA Polisi merupakan salah satu seleksi untuk jenis SCBA dimana setelah lulus nanti anda akan mendapatkan pekerjaan menjadi seorang anggota polisi pangkat Britu, Bripda atau lainnya, ini merupakan seleksi yang paling banyak diminati hanya saja untuk bisa mengikuti seleksi ini anda harus mendaftarkan diri melalui online http://penerimaan.polri.go.id/ segera lakukan Pendaftaran sesuai dengan kebutuhan dan juga sesuai identitas diri anda. Bisa diikuti oleh lulusan SMA/SMK/MA sederajat.\n"
            )
        )
        data.add(
            ModelInformasi(
                "221",
                "Pendaftaran online BINTARA khusus penyidik pembantu Polisi",
                "BINTARA Polisi merupakan salah satu seleksi untuk jenis SCBA dimana setelah lulus nanti anda akan mendapatkan pekerjaan menjadi seorang anggota polisi pangkat Britu, Bripda atau lainnya, ini merupakan seleksi yang paling banyak diminati hanya saja untuk bisa mengikuti seleksi ini anda harus mendaftarkan diri melalui online http://penerimaan.polri.go.id/ segera lakukan Pendaftaran sesuai dengan kebutuhan dan juga sesuai identitas diri anda. Bisa diikuti oleh lulusan SMA/SMK/MA sederajat.\n"
            )
        )
        data.add(
            ModelInformasi(
                "221",
                "Pendaftaran online TAMTAMA Polisi",
                "TAMTAMA Polisi merupakan salah satu jenis rekrtumen yang bisa anda ikuti sesuai ketentuan yang berlaku biasanya penerimaan ini dibuka untuk satuan Brimob TAMTAMA merupakan ajudan Bintara yang lebih besar peluang Pendaftarnya jika dibandingkan dengan penerimaan lainnya ini bisa dijadikan peluang untuk lulus Menjadi anggota Polisi untuk pendaftaran dilaksanakan secara online malalui situs resmi http://penerimaan.polri.go.id/.\n" +
                        "\n" +
                        "Selanjutnya persyaratan terbaru dapat langsung di cek melalui situs sebelum melakukan pengisian form pendaftaran, silahkan lengkapi terlebih dahulu agar bisa melanjutkan pendaftaran hingga selesai.\n" +
                        "\n" +
                        "Selanjutnya kita akan memhas mengenai tata cara Pendaftaran yang ada pada situs resmi penerimaan ini akan disampaikan melalui beberapa proses pendaftaran yang ada untuk lebih jelas kami akan memberikan informasinya seperti dibawah ini secara lengkap. Ada 6 cara mudah dalam pengisian formulir pendaftaran."
            )
        )
        binding.IDDaftarPolisiInformasiRecyclerView.layoutManager =
            LinearLayoutManager(requireContext())
        binding.IDDaftarPolisiInformasiRecyclerView.adapter = adapter
        adapter.setData(data)
binding.IDDaftarPolisiInformasiBtnBack.setOnClickListener {
    findNavController().popBackStack()
}

    }

}