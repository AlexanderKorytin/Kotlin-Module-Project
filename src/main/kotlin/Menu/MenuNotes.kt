package Menu

import Note

class MenuNotes(val nameArchiveNotes: String, val mapOfMenuItemNotes: MutableMap<String, ()->Unit>): MenuArchive(mapOfMenuItemNotes) {

    override fun create() {
        val name: String = scanStr()
        mapOfArchiveNotes[nameArchiveNotes]?.put(name, Note("Пустая заметка"))
        mapOfMenuItemNotes.clear()
        mapOfMenuItemNotes.put("Создать", {create()})
        mapOfArchiveNotes[nameArchiveNotes]?.onEach{entry -> mapOfMenuItem.put(entry.key, {selectedArchive(name)}) }
        mapOfMenuItemNotes.putIfAbsent("Выход", {exit()})
    }

    override fun exit() {
        mapOfMenuItem.clear()
        mapOfMenuItem.put("Создать", {create()})
        mapOfArchiveNotes.onEach{entry -> mapOfMenuItem.put(entry.key, {selectedArchive(entry.key)}) }
        mapOfMenuItem.putIfAbsent("Выход", {super.exit()})
        val menu = MenuArchive(mapOfMenuItem)
        while(true){
            menu.printMenu("\nСписок Архивов заметок\n", mapOfMenuItem)
            menu.choiceMenuItem()
        }
    }

    fun selectedNote(){

    }


}
