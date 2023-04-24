package Menu

import Note
import java.util.*

// класс реализующий общую логику всех меню

abstract class Menu(val mapOfMenu: MutableMap<String, () -> Unit>) {

    private val listOfItem: MutableList<String> =
        mutableListOf() // лист содержащий пункты меню. Необходим для вывода номерованных пунктов меню в консоль,
    // при работе функции printMenu()

    // поскольку пунты меню заметки остаются неизменными в отличие от всех остальных меню. названия архивов и заметок выводятся в списке пунктов меню
    companion object {
        val scan = Scanner(System.`in`)
        val mapOfArchiveNotes: MutableMap<String, MutableMap<String, Note>> =
            mutableMapOf() // основная память всей программы. Map где по названию архива
        // содержиться Map заметок: ключ - название заметки, значение - экземпляр класса Note, поле context которого - содержание заметки
    }

    // функция считывающая целочисленное значение в пределах min<..<max из консоли
    fun scanInt(min: Int, max: Int): Int {
        while (true) {
            try {
                val scanInt = scan.nextInt()
                when (scanInt) {
                    in min..max -> return scanInt
                    else -> {
                        println("Введите число от $min до $max")
                    }
                }
            } catch (ex: InputMismatchException) {
                println("Введите число от $min до $max")
                scan.nextLine()
            }
        }
    }

    // функция считывающая строку из консоли
    fun scanStr(message: String): String {
        println("введите название $message")
        while (true) {
            scan.reset()
            scan.nextLine()
            if (scan.hasNextLine()) {
                return scan.nextLine()
            }
        }
    }

    // функция вывода в консоль меню, формируемого из mapOfMenuItem: MutableMap<String, () -> Unit>
    // переводом в listOfItem. выводит номерованное меню
    fun printMenu(message: String, mapOfMenuItem: MutableMap<String, () -> Unit>, context: String?) {
        println(message) // заголовок
        if (context != null) println(context) // вывод содержания заметки
        listOfItem.clear()
        mapOfMenuItem.onEachIndexed { index, entry -> listOfItem.add(index, entry.key) }
        listOfItem.forEachIndexed { index, s -> println("${index} ${s}") }
    }

    // функция вызывающая лямбду из спмска меню. Считываем из консоли целочисленное значение и по нему получаем из listOfItem
    // строковый ключ для списка меню mapOfMenu. далее выполняем функцию из mapOfMenu.
    fun choiceMenuItem() {
        val item = scanInt(0, mapOfMenu.size - 1)
        mapOfMenu[listOfItem[item]]?.invoke()
    }

    // функция запускающая в цикле работу с меню, список которого передан в mapOfMenu
    // выводит список пунктов меню и выполняет функцию выбранного
    fun run(message: String, mapOfMenu: MutableMap<String, () -> Unit>, context: String?) {
        while (true) {
            printMenu(message, mapOfMenu, context)
            choiceMenuItem()
        }
    }
}