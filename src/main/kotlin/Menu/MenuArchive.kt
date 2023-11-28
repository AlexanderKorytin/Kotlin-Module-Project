package Menu

import kotlin.system.exitProcess
// меню списка архивов на вход передаем список пунктов меню: ключ - название, значение - функция
open class MenuArchive(val mapOfMenuItem: MutableMap<String, ()->Unit>): Menu(mapOfMenuItem) {
// функция создания нового архива
// инициализируем пункты меню списка архивов (initmenuArchive()) по принципу: первым пунктом - "создать" с функцией create(),
//  далее список добавленных архивов с функцией вызова меню списка заметок selectedArchive(name: String)
// далее "удалить" с функцией deleted() (пунк добавляется если в списке есть хотя бы один архив)
// далее "выход" с функцией exit()
    open fun create() {
        val name = scanStr("архива") // читаем из консоли
        mapOfArchiveNotes.put(name, mutableMapOf()) // добавляем пустой архив в "память"
        initmenuArchive()
    }
     // функция удаления архива
     open fun deleted(){
        val listOfDel = mutableListOf<String>()
        mapOfArchiveNotes.onEachIndexed{ index, entry ->  listOfDel.add(index, entry.key)
                                                           println("$index ${entry.key}")
        }
        println("Введите номер архива для удаления:")
        val indexItemDel = scanInt(0, mapOfArchiveNotes.size-1)
        mapOfArchiveNotes.remove(listOfDel[indexItemDel])
        initmenuArchive()
    }
    // заверщение программы
   open fun exit() {
        scan.close()
        exitProcess(0)
    }

   // функция инициализации и вывода в консоль меню списка заметок выбранного архива
   fun selectedArchive(name: String){
        val mapOfMenuItemNotes: MutableMap<String, () -> Unit> = mutableMapOf()
        val menu = MenuNotes(name, mapOfMenuItemNotes)
        menu.initmenuNotes()
        menu.run("\nСписок заметок.\n  \nАрихив $name.\n----------\n Меню:", mapOfMenuItemNotes, context = null)
    }
    // функция инициализации меню списка архивов. принцип описан выше (функция create())
    fun initmenuArchive(){
        mapOfMenuItem.clear()
        mapOfMenuItem.put("Создать", {create()})
        if(!mapOfArchiveNotes.isEmpty())mapOfArchiveNotes.onEach{ entry -> mapOfMenuItem.put(entry.key, {selectedArchive(entry.key)}) }
        if(!mapOfArchiveNotes.isEmpty()) mapOfMenuItem.putIfAbsent("Удалить", {deleted()})
        mapOfMenuItem.putIfAbsent("Выход", {exit()})
    }
}


