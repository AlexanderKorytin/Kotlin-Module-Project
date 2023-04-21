package Menu

import kotlin.system.exitProcess

open class MenuArchive(val mapOfMenuItem: MutableMap<String, ()->Unit>): Menu(mapOfMenuItem) {

    open fun create() {
        val name = scanStr("архива")
        mapOfArchiveNotes.put(name, mutableMapOf())
        initmenuArchive()
    }

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

   open fun exit() {
        exitProcess(0)
    }


   fun selectedArchive(name: String){
        val mapOfMenuItemNotes: MutableMap<String, () -> Unit> = mutableMapOf()
        val menu = MenuNotes(name, mapOfMenuItemNotes)
        menu.initmenuNotes()
        menu.run("\nСписок заметок.\n  \nАрихив $name.\n----------\n Меню:", mapOfMenuItemNotes)
    }
    fun initmenuArchive(){
        mapOfMenuItem.clear()
        mapOfMenuItem.put("Создать", {create()})
        if(!mapOfArchiveNotes.isEmpty())mapOfArchiveNotes.onEach{ entry -> mapOfMenuItem.put(entry.key, {selectedArchive(entry.key)}) }
        if(!mapOfArchiveNotes.isEmpty()) mapOfMenuItem.putIfAbsent("Удалить", {deleted()})
        mapOfMenuItem.putIfAbsent("Выход", {exit()})
    }
}


