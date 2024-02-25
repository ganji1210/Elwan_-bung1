# MAD - Exercise 01
## Tasks
* Watch the Kotlin Crashcourse Video in Moodle or complete the tutorials **[Introduction to programming in Kotlin](https://developer.android.com/courses/pathways/android-basics-compose-unit-1-pathway-1)** and **[Kotlin fundamentals](https://developer.android.com/courses/pathways/android-basics-compose-unit-2-pathway-1
)**.
* Answer the questions inside this Readme.md file and push it to your repository.
* Submit your coding solution of the Number Guessing Game inside the repository.
* Submit the link to your repository in Moodle.

## Questions
### Describe how Kotlin handles null safety. What are nullable types and non-null types in Kotlin? (0,5 points)
Kotlin is designed to eliminate the NullPointerException from the code, which is often referred to as "The Billion Dollar Mistake." 
It introduces a system of null safety in the type system itself, 
distinguishing between nullable and non-null types, thus enforcing explicit handling of null values. 
Here's how it works:

Non-Null Types
In Kotlin, types are non-null by default. This means that if you declare a variable without specifying it can hold a null value, 
trying to assign null to it will result in a compile-time error. 
This behavior prevents the accidental use of null values and eliminates a common source of runtime errors.

```kotlin 
var nonNullableString: String = "Hello, Kotlin!"
// nonNullableString = null // This line would cause a compile-time error.

```
Nullable Types

To allow a variable to hold a null value, you must explicitly mark it as nullable by adding a ? after the type name.
This makes it clear in the code that the variable might not hold a value, requiring you to handle the potential null case explicitly,
either by checking for null before using it or by using safe call operators.

```kotlin 
var nullableString: String? = "I might be null."
nullableString = null // This is allowed because nullableString is a nullable type.

```
Handling Nullable Types
Kotlin provides several mechanisms to safely work with nullable types:

Safe Calls (?.): Allows you to safely access a property or method of a nullable object. If the object is null, 
the safe call expression evaluates to null without throwing an exception.

```kotlin 
val length: Int? = nullableString?.length // length is null if nullableString is null.

```
Elvis Operator (?:): Provides a way to specify 
a default value to use when the expression on the left-hand side of ?: is null.

```kotlin 
val lengthOrDefault: Int = nullableString?.length ?: 0 // Uses 0 if nullableString is null.

```

Non-Null Assertion (!!): Converts any value to a non-null type and throws 
a NullPointerException if the value is null.
Use this operator only when you are absolutely sure the value won't be null; 
otherwise, it defeats the purpose of Kotlin's null safety.

```kotlin 
val length: Int = nullableString!!.length // Throws NullPointerException if nullableString is null.

```
Safe Casts (as?): Safely casts a variable to the specified type. 
If the variable cannot be cast, it evaluates to null instead of throwing a ClassCastException.

```kotlin 
val safeString: String? = someValue as? String // safeString is null if someValue cannot be cast to String.

```

### What are lambda expressions and higher order functions in Kotlin? Why would you store a function inside a variable? (0,5 points)
A lambda expression is essentially an anonymous function that can be passed around as if it were a value. 
It can be assigned to a variable, 
passed as an argument to a function, or returned from a function. 
The syntax for a lambda is as follows:

```kotlin 
val sum = { a: Int, b: Int -> a + b }

```
Here, sum is a lambda expression that takes two integers and returns their sum.

Lambda expressions are particularly useful when working with collections, 
as they can be used as arguments for methods like filter, map, etc.:

```kotlin 
val numbers = listOf(1, 2, 3, 4, 5)
val evenNumbers = numbers.filter { it % 2 == 0 } // Using a lambda to filter even numbers

```

Higher-Order Functions
A higher-order function is a function that takes another function as an argument or returns a function. 
This is useful when you want to abstract out a part of a function's logic, making it more flexible and reusable.

For example, a simple higher-order function that takes a lambda as a parameter looks like this:

```kotlin 
fun calculate(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
    return operation(a, b)
}

val sumResult = calculate(4, 5, sum) // Pass the lambda expression 'sum' as a parameter

```
Here, calculate is a higher-order function that takes two integers 
and a function that describes an operation on these integers.


Storing Functions in Variables

You would store a function in a variable for several reasons:

Reusability: You can define a common operation as a lambda expression and reuse it in multiple places in your code.
Passing Logic: You can pass the logic encapsulated in the lambda to a higher-order function, enabling powerful patterns like callbacks.
Deferred Execution: Storing a lambda in a variable allows you to execute the code contained within it at a later time, on-demand.
Abstraction: It can help in abstracting complex operations, making the codebase cleaner and more readable.
Functional Constructs: Functions as variables are essential for functional programming constructs like chaining operations on collections.
Custom Behavior: You can use lambda expressions to provide custom behavior that can be plugged into methods, like sorting algorithms.

In Kotlin, functions are first-class citizens, which means they can be stored in variables, 
passed as arguments, and returned from other functions, just like any other value. 
This approach facilitates a functional style of programming that can lead to more expressive and concise code.

### Provide a solution for the following number guessing game inside `App.kt`. (3 points)

## Number Guessing Game in Kotlin
The game is a simple number guessing game. The task is to generate a random, max 9-digit, number. The number must **not contain repeating digits**. Valid digits are 1-9.
Ask the user to guess the max 9-digit number. The game is finished when the user guesses the correct digits in the correct order.
In each round, the user gets feedback about the number of correct digits and the number of correct digits in the correct position.
The output should be in the format "n:m", where "n" is the number of digits guessed correctly regardless of their position, 
and "m" is the number of digits guessed correctly at their correct position. Here are some examples:

This example shows the game flow with 4-digits to guess (the default argument)

Generated number: 8576
-	User input: 1234, Output: 0:0
-	User input: 5678, Output: 4:1
-	User input: 5555, Output: 1:1
-	User input: 3586, Output: 3:2
-	User input: 8576, Output: 4:4 -> user wins

Take a look into the provided code structure in `src/main/kotlin/App.kt`. Implement the following methods (lambda expressions):
- _playNumberGame(digitsToGuess: Int = 4)_ (1 point): The main game loop that handles user input and game state. Make use of _generateRandomNonRepeatingNumber_ and _checkUserInputAgainstGeneratedNumber_ here. This function also utilizes a default argument 
- _generateRandomNonRepeatingNumber_ (1 point): A lambda expression that generates a random number with non-repeating digits of a specified length.
- _checkUserInputAgainstGeneratedNumber_ (1 point): A lambda expression that compares the user's input against the generated number and provides feedback.

``CompareResult.kt`` This class is a data structure which helps with bundling the result of the comparison of the user input and the generated number. Look at the toSting() and use it in your output.

Run the project with `./gradlew run` and test your implementation with the provided tests in `src/test/kotlin/AppTest.kt` with `./gradlew test`.

# Project Structure
The project is structured into two main Kotlin files:

**App.kt**: Contains the main game logic and functions.

**AppTest.kt**: Contains unit tests for the various functions in App.kt.

