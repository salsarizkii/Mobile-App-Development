abstract class mapel {
    abstract fun start()

    abstract fun stop()

    fun accelerate() {
        println("Accelerating...")
    }
}

class Motorcycle : mapel() {
    override fun start() {
        println("Motorcycle started")
    }

    override fun stop() {
        println("Motorcycle stopped")
    }
}

fun main() {

    val motorcycle = Motorcycle()
    motorcycle.start() 
    motorcycle.accelerate() 
    motorcycle.stop() 
}