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
            println("${index + 1}. ${mataKuliah.nama} (${mataKuliah.kode}) - ${mataKuliah.jam} Jam")
        }
    }
}

// Interface untuk merepresentasikan Daftar Jadwal
interface DaftarJadwal {
    fun tambahJadwal(jadwal: Jadwal)
    fun tampilkanDaftarJadwal()
}

// Class untuk merepresentasikan Daftar Jadwal Kuliah
class DaftarJadwalKuliah : DaftarJadwal {
    private val daftarJadwal = mutableMapOf<String, Jadwal>()

    override fun tambahJadwal(jadwal: Jadwal) {
        daftarJadwal[jadwal.hari] = jadwal
    }

    override fun tampilkanDaftarJadwal() {
        daftarJadwal.forEach { (_, jadwal) ->
            jadwal.tampilkanJadwal()
            println()
        }
    }
}

// fun main() {
    // Membuat beberapa mata kuliah
    val matkul1 = MataKuliah("MK001", "Pemrograman Dasar", 2)
    val matkul2 = MataKuliah("MK002", "Algoritma dan Struktur Data", 3)
    val matkul3 = MataKuliah("MK003", "Basis Data", 2)

    // Membuat jadwal kuliah
    val jadwalSenin = JadwalKuliah("Senin")
    jadwalSenin.tambahMataKuliah(matkul1)
    jadwalSenin.tambahMataKuliah(matkul2)

    val jadwalSelasa = JadwalKuliah("Selasa")
    jadwalSelasa.tambahMataKuliah(matkul3)

    // Membuat daftar jadwal kuliah
    val daftarJadwal = DaftarJadwalKuliah()
    daftarJadwal.tambahJadwal(jadwalSenin)
    daftarJadwal.tambahJadwal(jadwalSelasa)

    // Menampilkan daftar jadwal kuliah
    daftarJadwal.tampilkanDaftarJadwal()
}
