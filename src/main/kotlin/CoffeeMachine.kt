package coffeemachine

object CoffeeMachine {
    var water = 400
    var milk = 540
    var coffeeBeans = 120
    var coreIngredients = arrayOf(water, milk, coffeeBeans)
    var cups = 9
    var money = 550

    init {
        println("Coffee machine is on!")
    }

    fun selectAction(): Boolean {
        print("\nWrite action (buy, fill, take, remaining, exit): ")
        val input = readln()
        if (Actions.values().any { it.action == input }) {
            when (input) {
                "buy" -> buy()
                "fill" -> fill()
                "take" -> take()
                "remaining" -> printCurrentState()
                "exit" -> return false
            }
        } else {
            println("You must type buy, fill, take, remaining or exit")
            return true
        }

        return true
    }

    private fun printCurrentState() {
        println("\nThe coffee machine has:" +
                "\n${coreIngredients[0]} ml of water" +
                "\n${coreIngredients[1]} ml of milk" +
                "\n${coreIngredients[2]} g of coffee beans" +
                "\n$cups disposable cups" +
                "\n$$money of money\n")
    }

    private fun fill() {
        print("Write how many ml of water do you want to add: ")
        coreIngredients[0] += readln().toInt()
        print("Write how many ml of milk do you want to add: ")
        coreIngredients[1] += readln().toInt()
        print("Write how many grams of coffee beans do you want to add: ")
        coreIngredients[2] += readln().toInt()
        print("Write how many disposable cups of coffee do you want to add: ")
        cups += readln().toInt()
        println()
    }

    private fun take() {
        println("\nI gave you $$money")
        money = 0
    }

    private fun buy() {
        print("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, 4 - back (to main menu): ")
        val choice = readln()
        val notEnoughResources: Boolean
        if (!CoffeeSelection.values().any { it.options.contains(choice) }) {
            println("Invalid choice!")
            buy()
        } else {
            when (choice) {
                in CoffeeSelection.BACK.options -> selectAction()
                in CoffeeSelection.ESPRESSO.options -> {
                    if (cups <= 0) {
                        println("Sorry, not enough cups!")
                    } else {
                        notEnoughResources = checkCoreIngredients(ESPRESSO)

                        if (!notEnoughResources) {
                            makeCoffee(ESPRESSO, espressoCost)
                        }
                    }
                }
                in CoffeeSelection.LATTE.options -> {
                    if (cups <= 0) {
                        println("Sorry, not enough cups!")
                    } else {
                        notEnoughResources = checkCoreIngredients(LATTE)

                        if (!notEnoughResources) {
                            makeCoffee(LATTE, latteCost)
                        }
                    }
                }
                in CoffeeSelection.CAPPUCCINO.options -> {
                    if (cups <= 0) {
                        println("Sorry, not enough cups!")
                    } else {
                        notEnoughResources = checkCoreIngredients(CAPPUCCINO)

                        if (!notEnoughResources) {
                            makeCoffee(CAPPUCCINO, cappuccinoCost)
                        }
                    }
                }
            }
        }
    }

    private fun checkCoreIngredients(coffeeType: Array<Int>): Boolean {
        var notEnoughResources = false
        for (index in 0..coreIngredients.size - 1) {
            if (coreIngredients[index] < coffeeType[index]) {
                println(when (index) {
                    0 -> "Sorry, not enough water!"
                    1 -> "Sorry, not enough milk!"
                    2 -> "Sorry, not enough coffee beans!"
                    else -> continue
                })
                notEnoughResources = true
            }
        }
        return notEnoughResources
    }

    private fun makeCoffee(coffeeType: Array<Int>, cost: Int) {
        println("I have enough resources, making you a coffee!")
        cups--
        for (index in 0..coreIngredients.size - 1) {
            coreIngredients[index] -= coffeeType[index]
        }
        money += cost
    }
}
