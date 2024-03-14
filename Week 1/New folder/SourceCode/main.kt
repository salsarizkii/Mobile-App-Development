

import kotlin.text.toInt
import kotlin.math.pow

fun main(){
    val header = """
    -----------------------------------------------
    -|              SWEET CALCULATOR             |-
    -----------------------------------------------
    """

    val mainMenu = """-----------------------------------------------
    -| [1] Penjumlahan                           |-
    -| [2] Pengurangan                           |-
    -| [3] Perkalian                             |-
    -| [4] Bagi (Pembagian)                      |-
    -| [5] Menghitung banyak karakter            |-
    -| [6] Pangkat                               |-
    -| [0] Gajadi, deh! Ga mood.                 |-
    -----------------------------------------------
    """

    print(header)
    print(mainMenu)
    println("Masukkan pilihanmu")
    print("-> ")
    var firstChoise = readLine()!!.toInt()
    println("-----------------------------------------------")

    if (firstChoise == 0){
        System.exit(0)
    }

    println("input pertama")
    print("-> ")
    var firstNum = readLine()!!
    println("input kedua (\"skip untuk string\")")
    print("-> ")
    var scdNum = readLine()!!
    println("-----------------------------------------------")


    if (firstChoise == 1){
        add(num1 = firstNum.toInt(), num2 = scdNum.toInt())
    } else if (firstChoise == 2){
        sub(firstNum.toInt(), scdNum.toInt())
    } else if (firstChoise == 3){
        multiply(firstNum.toInt(), scdNum.toInt())
    } else if  (firstChoise == 4){
        divide(firstNum.toInt(), scdNum.toInt())
    } else if (firstChoise == 5){
        howManyChar(firstNum)
    } else if (firstChoise == 6){
        pangkat(firstNum.toInt(), scdNum.toInt())
    } else {
        println("Maaf, pilihanmu tidak tersedia.")
        main()
    }
}
fun add(num1: Int, num2: Int): Int{
    var result: Int = num1 + num2
    println("Hasil Penjumlahan dari $num1 + $num2 adalah $result")
    println("-----------------------------------------------")
    return result
}

fun sub(num1: Int = 0, num2: Int = 0): Int{
    var result: Int = num1 - num2
    println("Hasil Pengurangan dari $num1 - $num2 adalah $result")
    println("-----------------------------------------------")
    return result
}

fun multiply(num1: Int, num2: Int): Int{
    var result: Int = num1 * num2
    println("Hasil Perkalian dari $num1 * $num2 adalah $result")
    println("-----------------------------------------------")
    return result
}

fun divide(num1: Int, num2: Int): Int?{
    var result: Int?
    if (num2 == 0){
        println("pembagian 0 tuh \"GABISA\" ya gais!")
        result = null
    } else {
        result = num1 / num2
    }

    println("Hasil Pembagian dari $num1 / $num2 adalah ${result}")
    println("-----------------------------------------------")
    return result
}

fun howManyChar(word1: String?): Int?{
    var result: Int? = word1?.length
    println("banyak character dari $word1 adalah $result")
    println("-----------------------------------------------")
    return result
}

fun pangkat(num1: Int, num2: Int): Float{
    var result: Float = num1.toFloat().pow(num2.toFloat())
    println("Hasil Perpangkatan dari $num1 pangkat $num2 adalah $result")
    println("-----------------------------------------------")
    return result
}
