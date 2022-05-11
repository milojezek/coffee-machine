package coffeemachine

fun main() {
    var coffeeMachineIsOn = true
    while (coffeeMachineIsOn) {
        coffeeMachineIsOn = CoffeeMachine.selectAction()
    }
}