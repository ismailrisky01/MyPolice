package com.example.myapplication

import com.example.myapplication.Constants.OPEN_GOOGLE
import com.example.myapplication.Constants.OPEN_SEARCH
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import kotlin.random.Random

object BotResponse {

    fun basicResponses(_message: String): String {

        val random = Random.nextInt(0,2)
        val message =_message.toLowerCase()

        return when {

            //Flips a coin
            message.contains("flip") && message.contains("coin") -> {
                val r = Random.nextInt(0,1)
                val result = if (r == 0) "heads" else "tails"

                "I flipped a coin and it landed on $result"
            }

            //Math calculations
            message.contains("solve") -> {
                val equation: String? = message.substringAfterLast("solve")
                return try {
                    val answer = SolveMath.solveMath(equation ?: "0")
                    "$answer"

                } catch (e: Exception) {
                    "Sorry, I can't solve that."
                }
            }

            //Hello
            message.contains("hello") -> {
                when (random) {
                    0 -> "Hello there!"
                    1 -> "Sup"
                    2 -> "Buongiorno!"
                    else -> "error" }
            }

            //How are you?
            message.contains("how are you") -> {
                when (random) {
                    0 -> "I'm doing fine, thanks!"
                    1 -> "I'm hungry..."
                    2 -> "Pretty good! How about you?"
                    else -> "error"
                }
            }
            message.contains("1") -> {
                "Fitur halo polisi terdiri dari beberapa menu yakni laporan tindak kejahatan, lapor tanggap bencana, pemadam kebakaran dan panduan keselamatan yang disediakan dalam aplikasi ini. Pada fitur ini, masyarakat dapat menghubungi Polisi sesuai dengan kebutuhannya. Dan juga terdapat informasi lainnya seperti lokasi Polres, UGD dan SPBU terdekat. Didalam fitur halo polisi juga melayani tanggap Covid-19 sebagai bentuk kepedulian Polisi di tengah wabah Covid-19 yang mana nantinya Polisi bekerjasama dengan Puskesmas, klinik atau rumah sakit terdekat untuk mengoptimalkan pelayanan kesehatan dan keamanan kepada masyarakat."
            }
            message.contains("2") -> {
                "Pengguna MyPolice dapat menghubungi kepolisian secara langsung melalui aplikasi cukup dengan paket internet .Aktif 24 jam pelayanan. Fitur telepon ini sebagai pembantu pengguna MyPolice untuk lapor baik tindak kejahatan, bencana alam hingga layanan darurat. Jika pengguna menayalahgunakan fitur telepon ini maka akan terdata secara otomoatis dan ditindaklanjuti."
            }       message.contains("3") -> {
                "Melalui aplikasi ini, pengguna dapat mengurus SIM dan surat-surat kendaraan seperti STNK dan BPKB. Pengguna dapat mendaftar sebagai calon peserta ujian SIM dan mempersiapkan diri untuk menjalani ujian pembuatan SIM melalui fitur simulasi ujian yang disediakan didalam 7 aplikasi. Di menu ini juga diperlihatkan prosedur-prosedur yang harus dilakukan untuk membuat SIM. Selain itu, melalui menu ini pengguna dapat memroses pembuatan BPKB dan perpanjangan STNK melalui satu pintu MyPolice."
            }       message.contains("4") -> {
                "Pada halaman ini, pengguna akan ditunjukkan alur proses tilangan, mulai dari tanggal sidang, tempat sidang, hingga denda yang harus dibayarkan. Pengguna dapat mengakses informasi terkait Pengadilan Negeri yang harus didatangi untuk mengurusi proses tilang serta melakukan pembayaran melalui aplikasi MyPolice. Aplikasi ini juga bekerja sama dengan pihak bank dan aplikasi dana lainnya. Sehingga, pengguna dapat dengan mudah mengurus proses pembayaran melalui aplikasi MyPolice."
            }       message.contains("5") -> {
                "Pada fitur ini, untuk mengurus SKCK pengguna dapat mengurusnya secara online. SKCK online ini dapat langsung dicetak dengan persetujuan pihak kepolisian tanpa perlu mengisi data lagi, karena pendataan telah direkam sejak awal pengguna memasukkan data ketika login. Aplikasi ini dihubungkan dengan data rekam jejak masyarakat di catatan kepolisian, sehingga alur proses pembuatan SKCK dapat dilakukan tanpa ribet."
            }       message.contains("6") -> {
                "Fitur ini terhubung dengan sensor ultrasonik berbasis android yang terpasang dibawah bumper kendaraan bagian depan dan belakang. Penambahan sensor ini bertujuan untuk memonitoring terjadinya kecelakaan sehingga nantinya dapat mengirimkan data ke aplikasi jika terjadi benturan pada alat sensor tersebut. Antar muka dalam aplikasi ini meliputi halaman utama fitur monitor kecelakaan , halaman monitor akan menunjukkan data jika telah terjadi benturan yang terdeteksi disekitar kendaraan , dan tombol guide sebagai petunjuk.  Jika kecelakaan tersebut perlu memerlukan bantuan kepolisian maka dapat mengirim data otomatis dalam aplikasi dengan klik submit, namun jika kecelakaan ringan maka data berupa waktu , lokasi dan keterangan hanya tersimpan dalam akun pengguna."
            }       message.contains("7") -> {
                "Pada fitur ini akan disediakan rekam jejak kegiatan kepolisian yang telah dilakukan. Dari fitur ini, masyarakat dapat mengetahui kegiatankegiatan yang diadakan oleh kepolisian, dan juga terdapat informasi-informasi terbaru yang berkaitan dengan kebijakan terbaru."
            }       message.contains("8") -> {
                "Dalam fitur ini tersedia info mengenai tugas dan fungsi polisi sekalus informasi terkait pendaftaran menjadi polisi. "
            }
            //What time is it?
            message.contains("time") && message.contains("?")-> {
                val timeStamp = Timestamp(System.currentTimeMillis())
                val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
                val date = sdf.format(Date(timeStamp.time))

                date.toString()
            }

            //Open Google
            message.contains("open") && message.contains("google")-> {
                OPEN_GOOGLE
            }

            //Search on the internet
            message.contains("search")-> {
                OPEN_SEARCH
            }

            //When the programme doesn't understand...
            else -> {
                when (random) {
                    0 -> "I don't understand..."
                    1 -> "Try asking me something different"
                    2 -> "Idk"
                    else -> "error"
                }
            }
        }
    }
}