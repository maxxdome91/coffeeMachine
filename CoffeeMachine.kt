package machine

import kotlin.system.exitProcess

class CoffeeMachine {

    private enum class State {
        ACTION, BUY, FILL
    }

    private var state = State.ACTION

    private val stateOfMachine = mutableListOf(400, 120, 540, 9, 550)

    private val coffeeDatabase = mutableListOf(
        mutableListOf(250, 16, 0, 1, -4),
        mutableListOf(350, 20, 75, 1, -7),
        mutableListOf(200, 12, 100, 1, -6)
    )

    private val fillStatements = arrayOf<String>(
        "Write how many ml of water do you want to add: ",
        "Write how many ml of milk do you want to add: ", "Write how many grams of coffee beans do you want to add: ",
        "Write how many disposable cups of coffee do you want to add: "
    )

    private var fillPosition = 0

    fun operate(input: String) {
        when (state) {
            State.ACTION -> {
                when (input) {
                    "buy" -> {
                        println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
                        state = State.BUY
                    }

                    "fill" -> {
                        println(fillStatements[fillPosition])
                        state = State.FILL
                    }

                    "take" -> {
                        println("I gave you $${stateOfMachine[4]}")
                        stateOfMachine[4] = 0
                        println("Write action (buy, fill, take, remaining, exit): ")
                    }

                    "remaining" -> {
                        printState()
                        println("Write action (buy, fill, take, remaining, exit): ")
                    }
                    "exit" -> exitProcess(0)
                }
            }

            State.BUY -> {
                if (input == "back") {
                    state = State.ACTION
                    println("Write action (buy, fill, take, remaining, exit): ")
                }
                else {
                    if (hasEnough(input.toInt())) {
                        println("I have enough resources, making you a coffee!")
                        when (input.toInt()) {
                            1 -> {
                                for (i in stateOfMachine.indices) {
                                    stateOfMachine[i] -= coffeeDatabase[0][i]
                                }
                            }

                            2 -> {
                                for (i in stateOfMachine.indices) {
                                    stateOfMachine[i] -= coffeeDatabase[1][i]
                                }
                            }

                            3 -> {
                                for (i in stateOfMachine.indices) {
                                    stateOfMachine[i] -= coffeeDatabase[2][i]
                                }
                            }
                        }
                    }
                    state = State.ACTION
                    println("Write action (buy, fill, take, remaining, exit): ")
                }
            }

            State.FILL -> {
                stateOfMachine[fillPosition] += input.toInt()
                fillPosition++
                if (fillPosition == 4) {
                    fillPosition = 0
                    state = State.ACTION
                    println("Write action (buy, fill, take, remaining, exit): ")
                } else {
                    println(fillStatements[fillPosition])
                }
            }
        }
    }


    private fun printState(): Unit {
        println(
            """
        The coffee machine has:
        ${stateOfMachine[0]} ml of water
        ${stateOfMachine[2]} ml of milk
        ${stateOfMachine[1]} g of coffee beans
        ${stateOfMachine[3]} disposable cups
        $${stateOfMachine[4]} of money
    """.trimIndent()
        )
    }

    private fun hasEnough(choice: Int): Boolean {
        when (choice) {
            1 -> {
                for (i in stateOfMachine.indices - 1) {
                    if (stateOfMachine[i] >= coffeeDatabase[0][i]) continue
                    else {
                        when (i) {
                            0 -> println("Sorry, not enough water!")
                            1 -> println("Sorry, not enough coffee!")
                            2 -> println("Sorry, not enough milk!")
                            3 -> println("Sorry, not enough cups!")
                        }
                        return false
                    }
                }
            }

            2 -> {
                for (i in stateOfMachine.indices - 1) {
                    if (stateOfMachine[i] >= coffeeDatabase[1][i]) continue
                    else {
                        when (i) {
                            0 -> println("Sorry, not enough water!")
                            1 -> println("Sorry, not enough coffee!")
                            2 -> println("Sorry, not enough milk!")
                            3 -> println("Sorry, not enough cups!")
                        }
                        return false
                    }
                }
            }

            3 -> {
                for (i in stateOfMachine.indices - 1) {
                    if (stateOfMachine[i] >= coffeeDatabase[2][i]) continue
                    else {
                        when (i) {
                            0 -> println("Sorry, not enough water!")
                            1 -> println("Sorry, not enough coffee!")
                            2 -> println("Sorry, not enough milk!")
                            3 -> println("Sorry, not enough cups!")
                        }
                        return false
                    }
                }
            }
        }
        return true
    }
}
