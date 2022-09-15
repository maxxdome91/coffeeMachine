package machine

import kotlin.math.min

fun main() {
    val coffeeMaschine = CoffeeMachine()
    println("Write action (buy, fill, take, remaining, exit): ")
    while (true) {
        coffeeMaschine.operate(readln())
    }
}
