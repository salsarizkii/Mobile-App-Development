// Nama    : Salsa Rizki Saputri
// NIM     : L0122147
// Kelas   : D Informatika

/*
Program ini merupakan program pencatat jadwal yang dapat digunakan untuk mencatat jadwal kuliah. 
Program ini dapat digunakan untuk melakukan input jadwal perhari di hari weekdays yang terdiri dari kode matkul, 
nama mata kuliah, dan jam mata kuliah. Selain itu, user juga dapat melihat daftar jadwal sesuai harinya.
*/

// Class untuk merepresentasikan Mata Kuliah
data class MataKuliah(val kode: String, val nama: String, val jam: Int)

// Abstract class untuk merepresentasikan Jadwal
abstract class Jadwal(val hari: String) {
    abstract fun tambahMataKuliah(mataKuliah: MataKuliah)
    abstract fun tampilkanJadwal()
}

// Class untuk merepresentasikan Jadwal Kuliah
class JadwalKuliah(hari: String) : Jadwal(hari) {
    private val daftarMataKuliah = mutableListOf<MataKuliah>()

    override fun tambahMataKuliah(mataKuliah: MataKuliah) {
        daftarMataKuliah.add(mataKuliah)
    }

    override fun tampilkanJadwal() {
        println("Jadwal Kuliah Hari $hari:")
        daftarMataKuliah.forEachIndexed { index, mataKuliah ->
            println("${index + 1}. ${mataKuliah.nama} (${mataKuliah.kode}) - jam ke-${mataKuliah.jam}")
        }
    }
}

// Interface untuk merepresentasikan Daftar Jadwal
interface DaftarJadwal {
    fun tambahJadwal(jadwal: Jadwal)
    fun tampilkanJadwalHari(hari: String)
}

// Class untuk merepresentasikan Daftar Jadwal Kuliah
class DaftarJadwalKuliah : DaftarJadwal {
    private val daftarJadwal = mutableMapOf<String, Jadwal>()

    override fun tambahJadwal(jadwal: Jadwal) {
        daftarJadwal[jadwal.hari] = jadwal
    }

    override fun tampilkanJadwalHari(hari: String) {
        val jadwal = daftarJadwal[hari]
        if (jadwal != null) {
            jadwal.tampilkanJadwal()
        } else {
            println("Jadwal untuk hari $hari tidak ditemukan.")
        }
    }
}

fun main() {
    val daftarJadwal = DaftarJadwalKuliah()

    // lazy property
    val header by lazy {
        """
    -----------------------------------------------
    -|              MY - Schedules!              |-
    -----------------------------------------------
    """
    } 

    // lazy property
    val mainMenu by lazy {
     """
    -----------------------------------------------
    -| [1] Pilih ini nambah jadwal               |-
    -| [2] Pilih ini buat lihat jadwal           |-
    -| [0] Gajadi, deh! Ga mood.                 |-
    -----------------------------------------------
    """
    }
    do {
        print(header)
        print(mainMenu)
        print("Pilih menu: ")
        when (readLine()?.toIntOrNull()) {
            1 -> tambahJadwal(daftarJadwal)
            2 -> lihatJadwalHari(daftarJadwal)
            0 -> {
                println("Terima kasih!")
                return
            }
            else -> println("Pilihan tidak valid, silakan coba lagi.")
        }
    } while (true)
}

fun tambahJadwal(daftarJadwal: DaftarJadwal) {
    val pilihHari by lazy {
        """
    -----------------------------------------------
    -| [1] Senin                                 |-
    -| [2] Selasa                                |-
    -| [3] Rabu                                  |-
    -| [4] Kamis                                 |-
    -| [5] Jumat                                 |-
    -| [6] Gajadi, Balik Menu!                   |-
    -----------------------------------------------"""
    } 

    println(pilihHari)
    print("masukkan hari: ")
    val hariJadwal = readLine()?.toIntOrNull()
    val jadwalKuliah: JadwalKuliah

    jadwalKuliah = when (hariJadwal) {
        1 -> JadwalKuliah("Senin")
        2 -> JadwalKuliah("Selasa")
        3 -> JadwalKuliah("Rabu")
        4 -> JadwalKuliah("Kamis")
        5 -> JadwalKuliah("Jumat")
        6 -> return
        else -> {
            println("Pilihan tidak valid!")
            return
        }
    }

    while (true) {
        print("Kode Mata Kuliah (Ketik '00' untuk mengakhiri): ")
        val kode = readLine()?.trim() ?: break
        if (kode.equals("00", ignoreCase = true)) break

        print("Nama Mata Kuliah: ")
        val nama = readLine()?.trim() ?: break

        print("Jam Mata Kuliah: ")
        val jam = readLine()?.toIntOrNull() ?: break

        val mataKuliah = MataKuliah(kode, nama, jam)
        jadwalKuliah.tambahMataKuliah(mataKuliah)
    }

    daftarJadwal.tambahJadwal(jadwalKuliah)
    println("Jadwal berhasil ditambahkan untuk hari ${jadwalKuliah.hari}.")
}


fun lihatJadwalHari(daftarJadwal: DaftarJadwal) {
    val pilihHari by lazy {
        """
        -----------------------------------------------
        -| [1] Senin                                 |-
        -| [2] Selasa                                |-
        -| [3] Rabu                                  |-
        -| [4] Kamis                                 |-
        -| [5] Jumat                                 |-
        -| [6] Gajadi, Balik Menu!                   |-
        -----------------------------------------------"""
    } 

    println(pilihHari)
    print("masukkan hari: ")
    val hariJadwal = readLine()?.toIntOrNull()

    val hari = when (hariJadwal) {
        1 -> "Senin"
        2 -> "Selasa"
        3 -> "Rabu"
        4 -> "Kamis"
        5 -> "Jumat"
        6 -> return
        else -> {
            println("Pilihan tidak valid!")
            return
        }
    }

    daftarJadwal.tampilkanJadwalHari(hari)
}
