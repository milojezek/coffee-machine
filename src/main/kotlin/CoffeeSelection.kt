package coffeemachine

enum class CoffeeSelection(val options: Array<String>) {
    ESPRESSO(arrayOf("espresso", "1")),
    LATTE(arrayOf("latte", "2")),
    CAPPUCCINO(arrayOf("cappuccino", "3")),
    BACK(arrayOf("back", "4"))
}