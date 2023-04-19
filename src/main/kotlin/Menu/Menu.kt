package Menu

import Note
import java.util.*

open class Menu(val mapOfMenu: MutableMap<String, ()->Unit>) {
    private val listOfItem: MutableList<String> = mutableListOf()
    companion object {
        val mapOfArchiveNotes: MutableMap<String, MutableMap<String, Note>> = mutableMapOf()
    }

    fun scanInt(): Int {
        while (true) {
            val scan = Scanner(System.`in`)
            if (scan.hasNextInt()) {
                val scanInt = scan.nextInt()
                when (scanInt) {
                    in 0..mapOfMenu.size - 1 -> return scanInt
                    else -> println("Введите число от 0 до ${mapOfMenu.size - 1}")
                }
            } else println("Введите число от 0 до ${mapOfMenu.size - 1}")
        }
    }

    fun scanStr(): String {
        val scan = Scanner(System.`in`)
        println("введите название")
        while(true) {
            if(scan.hasNextLine()) {val scanString = scan.nextLine()
                return scanString}
        }
    }

    fun printMenu(message: String, mapOfMenuItem: MutableMap<String, () -> Unit>) {
        println(message)
        listOfItem.clear()
        mapOfMenuItem.onEachIndexed { index, entry -> listOfItem.add(index, entry.key) }
        listOfItem.forEachIndexed { index, s -> println("${index} ${s}") }
    }
    fun choiceMenuItem() {
        val item = scanInt()
        fun function() = mapOfMenu[listOfItem[item]]
        function()?.invoke()
    }

}