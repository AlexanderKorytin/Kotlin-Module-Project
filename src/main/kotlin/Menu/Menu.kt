package Menu

import Note
import java.util.*

// класс реализующий общую логику всех меню

open class Menu(val mapOfMenu: MutableMap<String, ()->Unit>) {

    private val listOfItem: MutableList<String> = mutableListOf() // лист содержащий пункты меню. Необходим для вывода номерованных пунктов меню в консоль,
    // при работе функции printMenu()
    var context: String? = null // переменная для вывода только сожержания конкретной заметки, сразу после заголовка меню заметки (MenuNote).
    // поскольку пунты меню заметки остаются неизменными в отличие от всех остальных меню. названия архивов и заметок выводятся в списке пунктов меню
    companion object {
        val mapOfArchiveNotes: MutableMap<String, MutableMap<String, Note>> = mutableMapOf() // основная память всей программы. Map где по названию архива
        // содержиться Map заметок: ключ - название заметки, значение - экземпляр класса Note, поле context которого - содержание заметки
    }

    // функция считывающая целочисленное значение в пределах min<..<max из консоли
    fun scanInt(min: Int, max: Int): Int {
        while (true) {
            val scan = Scanner(System.`in`)
            if (scan.hasNextInt()) {
                val scanInt = scan.nextInt()
                when (scanInt) {
                    in min..max -> return scanInt
                    else -> println("Введите число от $min до $max")
                }
            } else println("Введите число от $min до $max")
        }
    }

    // функция считывающая строку из консоли
    fun scanStr(message: String): String {
        val scan = Scanner(System.`in`)
        println("введите название $message")
        while(true) {
            if(scan.hasNextLine()) {val scanString = scan.nextLine()
                return scanString}
        }
    }

    // функция вывода в консоль меню, формируемого из mapOfMenuItem: MutableMap<String, () -> Unit>
    // переводом в listOfItem. выводит номерованное меню
    fun printMenu(message:String, mapOfMenuItem: MutableMap<String, () -> Unit>) {
        println(message) // заголовок
        if (context != null) println(context) // вывод содержания заметки
        listOfItem.clear()
        mapOfMenuItem.onEachIndexed { index, entry -> listOfItem.add(index, entry.key) }
        listOfItem.forEachIndexed { index, s -> println("${index} ${s}") }
    }

    // функция вызывающая лямбду из спмска меню. Считываем из консоли целочисленное значение и по нему получаем из listOfItem
    // строковый ключ для списка меню mapOfMenu. далее выполняем функцию из mapOfMenu.
    fun choiceMenuItem() {
        val item = scanInt(0, mapOfMenu.size-1)
        fun function() = mapOfMenu[listOfItem[item]]
        function()?.invoke()
    }
    // функция запускающая в цикле работу с меню, список которого передан в mapOfMenu
    // выводит список пунктов меню и выполняет функцию выбранного
    fun run(message: String, mapOfMenu: MutableMap<String, () -> Unit>){
        while(true){
            printMenu(message, mapOfMenu)
            choiceMenuItem()
        }
    }
}