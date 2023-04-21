package Menu

import Note

open class MenuNotes(val nameArchiveNotes: String, val mapOfMenuItemNotes: MutableMap<String, ()->Unit>): MenuArchive(mapOfMenuItemNotes) {

     override fun create() {
        val name: String = scanStr("заметки")
         mapOfArchiveNotes[nameArchiveNotes]?.put(name, Note("Пустая заметка"))
       initmenuNotes()
    }

     override fun exit() {
         val menu = MenuArchive(mapOfMenuItem)
         menu.initmenuArchive()
         menu.run("\nСписок Архивов заметок\n", mapOfMenuItem)
    }

    fun selectedNote(nameNote: String, nameArchive:String){
        val mapOfMenuItemNote: MutableMap<String, () -> Unit> = mutableMapOf()
        val menu = MenuNote(nameNote, nameArchive, mapOfMenuItemNote)
        menu.mapOfMenuItemNote.putIfAbsent("Редактировать заметку", {menu.create()})
        menu.mapOfMenuItemNote.putIfAbsent("Выход", {menu.exit()})
        menu.context = "${mapOfArchiveNotes[nameArchive]?.get(nameNote)}\n----------\nМеню:"
        menu.run("Заметка ${nameNote}.\nСодержание:",mapOfMenuItemNote)
    }
     override fun deleted(){
        val listOfDel = mutableListOf<String>()
        mapOfArchiveNotes[nameArchiveNotes]?.onEachIndexed{ index, entry ->  listOfDel.add(index, entry.key)
                                                                              println("$index ${entry.key}")
        }
        println("Введите номер заметки для удаления:")
        val indexItemDel = scanInt(0, (mapOfArchiveNotes[nameArchiveNotes]?.size?.minus(1)?:1))
        mapOfArchiveNotes[nameArchiveNotes]?.remove(listOfDel.get(indexItemDel))
        initmenuNotes()
    }
    fun initmenuNotes(){
        mapOfMenuItemNotes.clear()
        mapOfMenuItemNotes.put("Создать", {create()})
        if(mapOfArchiveNotes[nameArchiveNotes]?.isEmpty() == false) mapOfArchiveNotes[nameArchiveNotes]?.onEach{entry -> mapOfMenuItemNotes.put(entry.key, {selectedNote(entry.key, nameArchiveNotes)}) }
        if(mapOfArchiveNotes[nameArchiveNotes]?.isEmpty() == false) mapOfMenuItemNotes.putIfAbsent("Удалить", {deleted()})
        mapOfMenuItemNotes.putIfAbsent("Выход", {exit()})
    }
}

