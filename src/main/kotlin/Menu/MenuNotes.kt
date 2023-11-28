package Menu

import Note

// меню списка заметок на вход передаем список пунктов меню: ключ - название, значение - функция и название выбранного архива в фуекции selectedArchive(name: String)
// класса MenuArchive

open class MenuNotes(val nameArchiveNotes: String, val mapOfMenuItemNotes: MutableMap<String, () -> Unit>) :
    MenuArchive(mapOfMenuItemNotes) {
    // функция создания новогй заметки
    // инициализируем пункты меню списка заметок (nitmenuNotes()) по принципу: первым пунктом - "создать" с функцией create(),
    //  далее список добавленных заметок с функцией вызова меню заметки selectedNote(name: String)
    // далее "удалить" с функцией deleted() (пунк добавляется если в списке есть хотя бы одина заметка)
    // далее "выход" с функцией exit()
    override fun create() {
        val name: String = scanStr("заметки")
        mapOfArchiveNotes[nameArchiveNotes]?.put(name, Note("Пустая заметка"))
        initmenuNotes()
    }

    // функция инициализирует и выводит в консоль меню списка врхивов и формирует Map  - mapOfMenuItem c соответсвующими функциями
    override fun exit() {
        val menu = MenuArchive(mapOfMenuItem)
        menu.initmenuArchive()
        menu.run("\nСписок Архивов заметок\n", mapOfMenuItem, context = null)
    }

    // функция формирует и выводит в консоль содержание заметки и меню из двух пунктов "Редактировать заметку" и "Выход"
    fun selectedNote(nameNote: String, nameArchive: String) {
        val mapOfMenuItemNote: MutableMap<String, () -> Unit> = mutableMapOf()
        val menu = MenuNote(nameNote, nameArchive, mapOfMenuItemNote)
        menu.mapOfMenuItemNote.putIfAbsent("Редактировать заметку", { menu.create() })
        menu.mapOfMenuItemNote.putIfAbsent("Выход", { menu.exit() })
        menu.context = "${mapOfArchiveNotes[nameArchive]?.get(nameNote)}\n----------\nМеню:"
        menu.run("Заметка ${nameNote}.", mapOfMenuItemNote, menu.context)
    }

    // функция удаления заметки
    override fun deleted() {
        val listOfDel = mutableListOf<String>()
        mapOfArchiveNotes[nameArchiveNotes]?.onEachIndexed { index, entry ->
            listOfDel.add(index, entry.key)
            println("$index ${entry.key}")
        }
        println("Введите номер заметки для удаления:")
        val indexItemDel = scanInt(0, (mapOfArchiveNotes[nameArchiveNotes]?.size?.minus(1) ?: 1))
        mapOfArchiveNotes[nameArchiveNotes]?.remove(listOfDel.get(indexItemDel))
        initmenuNotes()
    }

    // функция инициализации меню списка архивов. принцип описан выше (функция create())
    fun initmenuNotes() {
        mapOfMenuItemNotes.clear()
        mapOfMenuItemNotes.put("Создать", { create() })
        if (mapOfArchiveNotes[nameArchiveNotes]?.isEmpty() == false) mapOfArchiveNotes[nameArchiveNotes]?.onEach { entry ->
            mapOfMenuItemNotes.put(
                entry.key,
                { selectedNote(entry.key, nameArchiveNotes) })
        }
        if (mapOfArchiveNotes[nameArchiveNotes]?.isEmpty() == false) mapOfMenuItemNotes.putIfAbsent(
            "Удалить",
            { deleted() })
        mapOfMenuItemNotes.putIfAbsent("Выход", { exit() })
    }
}

