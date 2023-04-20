package Menu

import Note

open class MenuNotes(val nameArchiveNotes: String, val mapOfMenuItemNotes: MutableMap<String, ()->Unit>): MenuArchive(mapOfMenuItemNotes) {

     override fun create() {
        val name: String = scanStr("заметки")
        mapOfArchiveNotes[nameArchiveNotes]?.put(name, Note("Пустая заметка"))
        mapOfMenuItemNotes.clear()
        mapOfMenuItemNotes.put("Создать", {create()})
        mapOfArchiveNotes[nameArchiveNotes]?.onEach{entry -> mapOfMenuItemNotes.put(entry.key, {selectedNote(entry.key, nameArchiveNotes)}) }
        mapOfMenuItemNotes.putIfAbsent("Удалить", {deleted()})
        mapOfMenuItemNotes.putIfAbsent("Выход", {exit()})
    }

     override fun exit() {
        mapOfMenuItem.clear()
        mapOfMenuItem.put("Создать", {super.create()})
        mapOfArchiveNotes.onEach{entry -> mapOfMenuItem.put(entry.key, {selectedArchive(entry.key)}) }
        mapOfMenuItem.putIfAbsent("Удалить", {super.deleted()})
        mapOfMenuItem.putIfAbsent("Выход", {super.exit()})
        val menu = MenuArchive(mapOfMenuItem)
        while(true){
            menu.printMenu("\nСписок Архивов заметок\n", mapOfMenuItem)
            menu.choiceMenuItem()
        }
    }

    fun selectedNote(nameNote: String, nameArchive:String){
        val mapOfMenuItemNote: MutableMap<String, () -> Unit> = mutableMapOf()
        val menu = MenuNote(nameNote, nameArchive, mapOfMenuItemNote)
        menu.mapOfMenuItemNote.putIfAbsent("Редактировать заметку", {menu.create()})
        menu.mapOfMenuItemNote.putIfAbsent("Выход", {menu.exit()})

        while(true){
            val message = "Заметка ${nameNote}:\n${mapOfArchiveNotes[nameArchive]?.get(nameNote)}.\n----------\nМеню:"
            menu.printMenu(message, mapOfMenuItemNote)
            menu.choiceMenuItem()
        }
    }
    override fun deleted(){

    }

}
