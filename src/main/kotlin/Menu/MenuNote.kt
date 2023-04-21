package Menu

import Note

class MenuNote(val nameNote: String,
               val nameArchive:String,
               val mapOfMenuItemNote: MutableMap<String, ()->Unit>
): MenuNotes(nameArchive, mapOfMenuItemNote) {
    override fun create() {
        val content: String = scanStr("Введите текст заметки:")
        mapOfArchiveNotes[nameArchive]?.put(nameNote, Note("Содержание: $content"))
        context = "$content\n----------\nМеню:"
    }

    override fun exit(){
        val menu = MenuNotes(nameArchive ,mapOfMenuItemNotes)
        menu.initmenuNotes()
        menu.run("\nСписок заметок.\n  \nАрихив $nameArchive.\n----------\n Меню:", mapOfMenuItemNotes)
    }
}