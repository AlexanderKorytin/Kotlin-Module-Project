import Menu.MenuArchive

fun main() {
    val mapOfMenu: MutableMap<String, () -> Unit> = mutableMapOf()
    val menu = MenuArchive(mapOfMenu)
    menu.mapOfMenuItem.putIfAbsent("Создать", {menu.create()})
    menu.mapOfMenuItem.putIfAbsent("Выход", {menu.exit()})
    while(true){
        menu.printMenu("\nСписок архивов заметок.\n----------\nМеню:" , mapOfMenu)
        menu.choiceMenuItem()
    }
}


