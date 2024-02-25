package at.ac.fhcampuswien

import kotlin.random.Random

class App {
    fun playNumberGame(digitsToGuess: Int = 4) {
        val generatedNumber = generateRandomNonRepeatingNumber(digitsToGuess)
        println("Welcome to the Number Guessing Game! Try to guess the $digitsToGuess-digit number.")
        var guess: Int
        do {
            println("Enter your guess:")
            guess = readLine()?.toIntOrNull() ?: 0
            val result = checkUserInputAgainstGeneratedNumber(guess, generatedNumber)
            println(result)
        } while (result.n != digitsToGuess || result.m != digitsToGuess)
        println("Congratulations! You've guessed the number correctly: $generatedNumber")
    }

    val generateRandomNonRepeatingNumber: (Int) -> Int = { length ->
        if (length < 1 || length > 9) throw IllegalArgumentException("Length must be between 1 and 9")
        val digits = (1..9).shuffled().take(length).joinToString("")
        digits.toInt()
    }

    val checkUserInputAgainstGeneratedNumber: (Int, Int) -> CompareResult = { input, generatedNumber ->
        val inputString = input.toString()
        val generatedString = generatedNumber.toString()

        if (inputString.length != generatedString.length) throw IllegalArgumentException("Input and generated number must have the same number of digits.")

        var correctDigits = 0
        var correctPositions = 0
        inputString.forEachIndexed { index, c ->
            if (generatedString.contains(c)) {
                correctDigits++
                if (generatedString[index] == c) correctPositions++
            }
        }
        CompareResult(correctDigits, correctPositions)
    }
}

fun main() {
    App().playNumberGame()
}
