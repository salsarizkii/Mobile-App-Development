import java.util.Scanner

// Interface untuk item pada to-do list
interface ToDoItem {
    val taskName: String
    val priority: String

    fun displayDetails()
}

// Kelas abstrak untuk menentukan task
abstract class Task(override val taskName: String, override val priority: String) : ToDoItem {
    abstract fun completeTask()
}

// Kelas turunan yang mewarisi dari Task
class SimpleTask(taskName: String, priority: String) : Task(taskName, priority) {
    override fun completeTask() {
        println("Tugas '$taskName' telah selesai.")
    }

    override fun displayDetails() {
        println("Tugas: $taskName, Prioritas: $priority")
    }
}

// Kelas untuk manajemen to-do list
class ToDoList {
    private val tasks = mutableListOf<ToDoItem>()

    // Fungsi untuk menambahkan task ke dalam list
    fun addTask(task: ToDoItem) {
        tasks.add(task)
        println("Tugas '${task.taskName}' telah ditambahkan ke dalam daftar.")
    }

    // Fungsi untuk menampilkan semua task dalam list
    fun displayTasks() {
        println("Daftar Tugas:")
        tasks.forEachIndexed { index, task ->
            println("${index + 1}. ${task.taskName} - Prioritas: ${task.priority}")
        }
    }

    // Fungsi untuk menyelesaikan task berdasarkan indeks
    fun completeTask(index: Int) {
        if (index in 1..tasks.size) {
            val task = tasks[index - 1] as? Task
            task?.completeTask() ?: println("Tugas tidak dapat diselesaikan.")
            tasks.removeAt(index - 1)
        } else {
            println("Indeks tidak valid.")
        }
    }
}

fun main() {
    val scanner = Scanner(System.`in`)
    val toDoList = ToDoList()

    // Menambahkan beberapa task awal
    toDoList.addTask(SimpleTask("Mengerjakan tugas kuliah", "Tinggi"))
    toDoList.addTask(SimpleTask("Beli bahan makanan", "Sedang"))
    toDoList.addTask(SimpleTask("Bersih-bersih rumah", "Rendah"))

    var choice: Int
    do {
        println("\nMenu:")
        println("1. Tampilkan Daftar Tugas")
        println("2. Tambah Tugas Baru")
        println("3. Selesaikan Tugas")
        println("4. Keluar")
        print("Pilih: ")
        choice = scanner.nextInt()

        when (choice) {
            1 -> toDoList.displayTasks()
            2 -> {
                print("Masukkan nama tugas: ")
                val taskName = scanner.next()
                print("Masukkan prioritas (Rendah/Sedang/Tinggi): ")
                val priority = scanner.next()
                toDoList.addTask(SimpleTask(taskName, priority))
            }
            3 -> {
                print("Masukkan nomor tugas yang sudah selesai: ")
                val taskIndex = scanner.nextInt()
                toDoList.completeTask(taskIndex)
            }
            4 -> println("Keluar dari program.")
            else -> println("Pilihan tidak valid.")
        }
    } while (choice != 4)
}
