package at.ac.fhcampuswien

class App {
    fun playNumberGame(digitsToGuess: Int = 4) {
        val generatedNumber = generateRandomNonRepeatingNumber(digitsToGuess)
        println("Welcome to the Number Guessing Game! Try to guess the $digitsToGuess-digit number.")

        var result: CompareResult

        do {
            println("Enter your guess:")
            val guess = readLine()?.toIntOrNull() ?: 0
            result = checkUserInputAgainstGeneratedNumber(guess, generatedNumber)
            println(result)
        } while (result.n != digitsToGuess || result.m != digitsToGuess)

        println("Congratulations! You've guessed the number correctly: $generatedNumber")
    }

    val generateRandomNonRepeatingNumber: (Int) -> Int = { length ->
        if (length < 1 || length > 9) throw IllegalArgumentException("Length must be between 1 and 9")
        val availableDigits = (1..9).toList()
        val digits = availableDigits.shuffled().take(length).joinToString("")
        digits.toInt()
    }

    val checkUserInputAgainstGeneratedNumber: (Int, Int) -> CompareResult = { input, generatedNumber ->
        val inputString = input.toString()
        val generatedString = generatedNumber.toString()

        if (inputString.length != generatedString.length) {
            throw IllegalArgumentException("Input and generated number must have the same number of digits.")
        }

        val inputDigits = inputString.toList()
        val generatedDigits = generatedString.toList()

        val correctPositions = inputDigits.zip(generatedDigits).count { it.first == it.second }
        val correctDigits = generatedDigits.intersect(inputDigits).size

        CompareResult(correctDigits, correctPositions)
    }
}

fun main() {
    App().playNumberGame()
}
