package Menu

import kotlin.system.exitProcess

open class MenuArchive(val mapOfMenuItem: MutableMap<String, ()->Unit>): Menu(mapOfMenuItem) {


    open fun create() {
        val name = scanStr("архива")
        mapOfArchiveNotes.put(name, mutableMapOf())
        mapOfMenuItem.clear()
        mapOfMenuItem.put("Создать", {create()})
        mapOfArchiveNotes.onEach{entry -> mapOfMenuItem.put(entry.key, {selectedArchive(entry.key)}) }
        mapOfMenuItem.putIfAbsent("Удалить", {deleted()})
        mapOfMenuItem.putIfAbsent("Выход", {exit()})
    }

    open fun deleted(){
        println("")
    }

   open fun exit() {
        exitProcess(0)
    }


   fun selectedArchive(name: String){
        val mapOfMenuItemNotes: MutableMap<String, () -> Unit> = mutableMapOf()
        val menu = MenuNotes(name, mapOfMenuItemNotes)
        menu.mapOfMenuItemNotes.putIfAbsent("Создать", {menu.create()})
        mapOfArchiveNotes[name]?.onEach { entry -> mapOfMenuItemNotes.put(entry.key, {menu.selectedNote(entry.key, name)}) }
        if (!mapOfArchiveNotes[name].isNullOrEmpty()) menu.mapOfMenuItemNotes.putIfAbsent("Удалить", {menu.deleted()})
        menu.mapOfMenuItemNotes.putIfAbsent("Выход", {menu.exit()})

        while(true){
            menu.printMenu("\nСписок заметок.\n  \nАрихив $name.\n----------\n Меню:", mapOfMenuItemNotes)
            menu.choiceMenuItem()
        }
    }
}


