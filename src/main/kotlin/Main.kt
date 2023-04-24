import Menu.MenuArchive

fun main() {
    val mapOfMenu: MutableMap<String, () -> Unit> = mutableMapOf() // формируем пустой список меню
    val menu = MenuArchive(mapOfMenu) // создаем объект меню списка архивов
    menu.mapOfMenuItem.putIfAbsent("Создать", {menu.create()})// добавляем пункт меню
    menu.mapOfMenuItem.putIfAbsent("Выход", {menu.exit()})// добавляем пунк меню
    menu.run("\nСписок архивов заметок.\n----------\nМеню:", mapOfMenu, null) // инициализируем и выводим меню списка архивов, ждем ввода из консоли
}


