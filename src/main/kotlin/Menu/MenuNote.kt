package Menu

import Note

class MenuNote(val nameNote: String,
               val nameArchive:String,
               val mapOfMenuItemNote: MutableMap<String, ()->Unit>
): MenuNotes(nameArchive, mapOfMenuItemNote) {
    override fun create() {
        val content: String = scanStr("Введите текст заметки:\n")
        mapOfArchiveNotes[nameArchive]?.put(nameNote, Note(content))
    }

    override fun exit(){
        mapOfMenuItemNotes.clear()
        mapOfMenuItemNotes.put("Создать", {super.create()})
        mapOfArchiveNotes[nameArchiveNotes]?.onEach{entry -> mapOfMenuItemNotes.put(entry.key, {selectedNote(entry.key, nameArchiveNotes)}) }
        mapOfMenuItemNotes.putIfAbsent("Удалить", {super.deleted()})
        mapOfMenuItemNotes.putIfAbsent("Выход", {super.exit()})
        val menu = MenuNotes(nameArchive ,mapOfMenuItemNotes)

        while(true) {
            menu.printMenu("\nСписок заметок.\n  \nАрихив $nameArchive.\n----------\n Меню:", mapOfMenuItemNotes)
            menu.choiceMenuItem()
        }
    }
}