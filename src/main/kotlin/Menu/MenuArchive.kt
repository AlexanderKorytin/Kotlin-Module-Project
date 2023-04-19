package Menu

import Note

import java.util.Scanner
import kotlin.system.exitProcess

open class MenuArchive(val mapOfMenuItem: MutableMap<String, ()->Unit>): Menu(mapOfMenuItem) {


    open fun create() {
        val name = scanStr()
        mapOfArchiveNotes.put(name, mutableMapOf())
        mapOfMenuItem.clear()
        mapOfMenuItem.put("Создать", {create()})
        mapOfArchiveNotes.onEach{entry -> mapOfMenuItem.put(entry.key, {selectedArchive(name)}) }
        mapOfMenuItem.putIfAbsent("Выход", {exit()})
    }

   open fun exit() {
        exitProcess(0)
    }


   fun selectedArchive(name: String){
        val mapOfMenuItemNotes: MutableMap<String, () -> Unit> = mutableMapOf()
        val menu = MenuNotes(name, mapOfMenuItemNotes)
        menu.mapOfMenuItemNotes.putIfAbsent("Создать", {menu.create()})
        mapOfArchiveNotes[name]?.onEach { entry -> mapOfMenuItemNotes.put(entry.key, {menu.selectedNote()}) }
        menu.mapOfMenuItemNotes.putIfAbsent("Выход", {menu.exit()})

        while(true){
            menu.printMenu("\nсписок заметок\n  \nАрихив $name\n --------", mapOfMenuItemNotes)
            menu.choiceMenuItem()
        }
    }
}


