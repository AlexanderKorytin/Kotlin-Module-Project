import Menu.MenuArchive

fun main(args: Array<String>) {
    val mapOfMenu: MutableMap<String, () -> Unit> = mutableMapOf()
    val menu = MenuArchive(mapOfMenu)
    menu.mapOfMenuItem.putIfAbsent("Создать", {menu.create()})
    menu.mapOfMenuItem.putIfAbsent("Выход", {menu.exit()})
    while(true){
        menu.printMenu("\nСписок Архивов заметок\n" , mapOfMenu)
        menu.choiceMenuItem()
    }
}


