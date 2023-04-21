package Menu

import Note

// класс меню заметки на вход передаем список пунктов меню: ключ - название, значение - функция
// название заметки и архива в функции selectedNote(nameNote: String, nameArchive:String) класса MenuNotes

class MenuNote(val nameNote: String,
               val nameArchive:String,
               val mapOfMenuItemNote: MutableMap<String, ()->Unit>
): MenuNotes(nameArchive, mapOfMenuItemNote) {

    // функция замены значения поля content экземпляра класса Note хранящегоя в списке mapOfArchiveNotes: MutableMap<String, MutableMap<String, Note>>
    // содержание заметки выводиться в консоль сразу после заголовка меню
    override fun create() {
        val content: String = scanStr("Введите текст заметки:")
        mapOfArchiveNotes[nameArchive]?.put(nameNote, Note("Содержание: $content"))
        context = "Содержание: $content\n----------\nМеню:" // переменная содержания заметки для вывода в сконсоль функцией printMenu.. класса Menu
    }
    // функция инициализирует и выводит в консоль меню списка заметок архива, ключ-название которого хранится в nameArchive
    override fun exit(){
        val menu = MenuNotes(nameArchive ,mapOfMenuItemNotes)
        menu.initmenuNotes()
        menu.run("\nСписок заметок.\n  \nАрихив $nameArchive.\n----------\n Меню:", mapOfMenuItemNotes)
    }
}