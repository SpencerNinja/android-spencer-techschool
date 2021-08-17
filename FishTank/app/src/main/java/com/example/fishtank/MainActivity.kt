package com.example.fishtank

import android.graphics.Color.YELLOW
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.fishtank.Aquarium5.fish5
import com.example.fishtank.Aquarium5.fishExamples
import com.example.fishtank.Decorations.Decorations
import com.example.fishtank.Decorations.makeDecorations
//import java.lang.Math.PI
import java.util.*
import kotlin.math.PI

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i("Test1", "Hello Kotlin, goodbye IntelliJ")


        var dirty = 20
        val waterFilter: (Int) -> Int = {dirty -> dirty / 2}
        fun feedFish(dirty: Int) = dirty + 10
        fun updateDirty(dirty: Int, operation: (Int) -> Int): Int {
            return operation(dirty)
        }
        fun dirtyProcessor() {
            dirty = updateDirty(dirty, waterFilter)
            dirty = updateDirty(dirty, ::feedFish)
            dirty = updateDirty(dirty, { dirty -> dirty + 50
            })
        }

        fun getDirtySensorReading() = 20
        fun shouldChangeWater(day: String, temperature: Int = 22, dirty: Int = getDirtySensorReading()): Boolean {
            val isTooHot = temperature > 30
            val isDirty = dirty > 30
            val isSunday = day == "Sunday"
            return when {
                isTooHot -> true
                isDirty -> true
                isSunday -> true
                else -> false
            }
        }
//        fun isTooHot(temperature: Int) = temperature > 30
//        fun isDirty(dirty: Int) = dirty > 30
//        fun isSunday(day: String) = day == "Sunday"
        fun fitMoreFish(tankSize: Double, currentFish: List<Int>, fishSize: Int = 2, hasDecorations: Boolean = true): Boolean {
            return (tankSize * if (hasDecorations) 0.8 else 1.0) >= (currentFish.sum() + fishSize)
        }
        fun randomDay() : String {
            val week = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
            return week[Random().nextInt(7)]
        }
        fun fishFood(day: String) : String {
            var food = "fasting"
            return when(day) {
                "Monday" -> "flakes"
                "Tuesday" -> "pellets"
                "Wednesday" -> "redworms"
                "Thursday" -> "granules"
                "Friday" -> "mosquitos"
                "Saturday" -> "lettuce"
                "Sunday" -> "plankton"
                else -> "fasting"
            }
        }
        fun eagerExample() {
            val decorations = listOf("rock", "pagoda", "plant", "alligator", "flowerpot")
            val eager = decorations.filter{it[0] == 'p'}
            Log.i("Test1", "$eager")
            // Apply filter lazily
            val filtered = decorations.asSequence().filter { it[0] == 'p' }
            Log.i("Test1", "$filtered")
            Log.i("Test1", "${filtered.toList()}")
            // Apply map lazily
            val lazyMap = decorations.asSequence().map {
                Log.i("Test1", "map: $it")
            }
            Log.i("Test1", "$lazyMap")
            Log.i("Test1", "first: ${lazyMap.first()}")
            Log.i("Test1", "all: ${lazyMap.toList()}")
        }
        fun feedTheFish() {
            val day = randomDay()
            val food = fishFood(day)
            Log.i("Test1", "Today is $day and the fish eat $food")
            shouldChangeWater(day = "Monday", dirty = 50)
            if (shouldChangeWater(day)) {
                Log.i("Test1", "Change the water today")
            }
            var bubbles = 0
            while (bubbles < 50) {
                bubbles++
            }
            repeat(2) {
                Log.i("Test1", "A fish is swimming")
            }
            // Call dirty processor
            dirtyProcessor()
        }
//        fun main(args: Array<String>) {
//            // println("Hello ${args[0]}!")
//            Log.i("Test1", "Hello ${args[0]}!")
//            feedTheFish()
//        }
        eagerExample()
        feedTheFish()

        main().buildAquarium()
        main().makeFish()

        makeDecorations()

        fishExamples()
    }
}


////In this practice, you will finish your simple game using higher-order functions, that is, a function that takes functions as an argument.
////In the game class, create a function move() that takes an argument called where, which is a lambda with no arguments that returns Unit.
////Hint: Declaring a function that takes a lambda as its argument:
////fun move(where: () -> Boolean )
////Inside move(), invoke the passed-in lambda.
////In the Game class, create a function makeMove() that takes a nullable String argument and returns nothing.
////Inside makeMove, test whether the String is any of the 4 directions and invoke move() with the corresponding lambda. Otherwise, invoke move() with end.
////Hint: You can call the function like this:
////move(north)
////In main() add a while loop that is always true.
////Inside the loop, print instructions to the player:
////print("Enter a direction: n/s/e/w:")
////Call makeMove() with the contents of the input from the user via readLine()
////Remove the code for testing the first version of your game.
////Run your program.
////Challenge:
////Create a simple “map” for your game, and when the user moves, show a description of their location. Consider the following:
////Use a Location class that takes a default width and height to track location. 4x4 is pretty manageable.
////You can create a matrix like this:
////val map = Array(width) { arrayOfNulls<String>(height) }
////Use an init block to initialize your map with descriptions for each location.
////When you move() also updateLocation(). There is some math involved to prevent null-pointer exceptions and keep the user from walking off the map. rem() and absoluteValue come handy.
////When you are done, zip up the code and send it to a friend to try your first Kotlin game.
//val map = Array(width) { arrayOfNulls<String>(height) }
//fun move(where: () -> Boolean ) {
//    where.invoke()
//}
//fun makeMove(command:String?) {
//    if (command.equals("n")) move(north)
//    else if (command.equals("s")) move(south)
//    else if (command.equals("e")) move(east)
//    else if (command.equals("w")) move(west)
//    else move(end)
//}
//while (true) {
//    print("Enter a direction: n/s/e/w: ")
//    game.makeMove(readLine())
//}


////Create an extension on List using a higher order function that returns all the numbers in the list that are divisible by 3. Start by creating an extension function on List that takes an lambda on Int and applies it to each item in the list. When the lambda returns zero, include the item in the output. For example, this list:
//val numbers = listOf<Int>(1,2,3,4,5,6,7,8,9,0)
//fun main(args: Array<String>) {
//    val numbers = listOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)
//    print(numbers.divisibleBy {
//        it.rem(3)
//    })
//}
//fun List<Int>.divisibleBy(block: (Int) -> Int): List<Int> {
//    val result = mutableListOf<Int>()
//    for (item in this) {
//        if (block(item) == 0) {
//            result.add(item)
//        }
//    }
//    return result
//}
////Output
////> [3, 6, 9, 0]


//Create a new file.
//Create an enum class, Directions, that has the directions NORTH, SOUTH, EAST and WEST, as well as START, and END.
//Create a class Game.
//Inside Game, declare a var, path, that is a mutable list of Direction. Initialize it with one element, START.
//Create 4 lambdas, north, south, east, and west, that add the respective direction to the path.
//Add another lambda, end, that:
//Adds END to path
//Prints “Game Over”
//Prints the path
//Clears the path
//Returns false
//Create a main function.
//Inside main(), create an instance of Game.
//To test your code so far, in main() print the path, then invoke north, east, south, west, and end. Finally, print the path again.
//enum class Direction {
//    NORTH, EAST, WEST, SOUTH, START, END
//}
//class Game {
//    var path = mutableListOf<Direction>(Direction.START)
//    val north = { path.add(Direction.NORTH) }
//    val south = { path.add(Direction.SOUTH) }
//    val east = { path.add(Direction.EAST) }
//    val west = { path.add(Direction.WEST) }
//    val end = { path.add(Direction.END); println("Game Over: $path"); path.clear(); false }
//}
//fun main(args: Array<String>) {
//    val game = Game()
//    println(game.path)
//    game.north()
//    game.south()
//    game.east()
//    game.west()
//    game.end()
//    println(game.path)
//}
//Output
//> [START]
//Game Over: [START, NORTH, SOUTH, EAST, WEST, END]
//[]


////Create a generic function for type BaseBuildingMaterial and call it isSmallBuilding, which takes a Building with a building material T as an argument. If the materials needed are less than 500, print "small building", otherwise, print "large building".
//fun <T : BaseBuildingMaterial> isSmallBuilding(building: Building<T>) {
//    if (building.actualMaterialsNeeded < 500) println("Small building")
//    else println("large building")
//}
//isSmallBuilding(Building(Brick()))


////Create a new package and file and call them Buildings.
////Create a class BaseBuildingMaterial with a property numberNeeded that is set to 1. You always need 1 of the base material.
////Create two subclasses, Wood and Brick. For BaseBuildingMaterial you need 4 units of wood or 8 units of brick. Now you have a type hierarchy.
////Create a generic class Building that can take any building material as its argument, and only building materials.
////A building always requires 100 base materials. Add a property baseMaterialsNeeded and set it to 100.
////Add another property, actualMaterialsNeeded and use a one-line function to calculate this from numberNeeded of the passed-in material.
////Add a method build() that prints the type and number of materials needed.
//open class BaseBuildingMaterial() {
//    open val numberNeeded = 1
//}
//class Wood : BaseBuildingMaterial() {
//    override val numberNeeded = 4
//}
//class Brick : BaseBuildingMaterial() {
//    override val numberNeeded = 8
//}
//class Building<T: BaseBuildingMaterial>(val buildingMaterial: T) {
//
//    val baseMaterialsNeeded = 100
//    val actualMaterialsNeeded = buildingMaterial.numberNeeded * baseMaterialsNeeded
//
//    fun build() {
//        println(" $actualMaterialsNeeded ${buildingMaterial::class.simpleName} required")
//    }
//}
//fun main(args: Array<String>) {
//    Building(Wood()).build()
//}
////Output
////400 Wood required


////Add a mutable property pages to Book.
////Create an extension function on Book that returns the weight of a book as the page count multiplied by 1.5 grams.
////Create another extension, tornPages(), that takes the number of torn pages as an argument and changes the page count of the book.
////Write a class Puppy with a method playWithBook() that takes a book as an argument, and removes a random number of pages from the book.
////Create a puppy and give it a book to play with, until there are no more pages.
//fun Book.weight() : Double { return (pages * 1.5) }
//fun Book.tornPages(torn: Int) = if (pages >= torn) pages -= torn else pages = 0
//class Puppy() {
//    fun playWithBook(book: Book) {
//        book.tornPages(Random().nextInt(12))
//    }
//}
//val puppy = Puppy()
//val book = Book("Oliver Twist", "Charles Dickens", 1837, 540)
//
//while (book.pages > 0) {
//    puppy.playWithBook(book)
//    println("${book.pages} left in ${book.title}")
//}
//println("Sad puppy, no more pages in ${book.title}. ")


////Create a top-level constant for the maximum number of books a person could borrow.
////Inside the Book class, create a method canBorrow() that returns true or false depending on whether a user has already borrowed the max number of books.
////Create a Constants object that provides constants to the book. For this example, provide the BASE_URL for all books in the library catalog. Inside Book, add a method printUrl that creates and prints a URL composed of BASE_URL, the book title, and “.html”.
////The base URL is really of interest to the Book class. As such, it makes sense to limit its scope to the Book class. Use a companion object to define the constant in Book.
//const val MAX_NUMBER_BOOKS = 20
//fun canBorrow(hasBooks: Int): Boolean {
//    return (hasBooks < MAX_NUMBER_BOOKS)
//}
//object Constants {
//    const val BASE_URL = "http://www.turtlecare.net/"
//}
//fun printUrl() {
//    println(Constants.BASE_URL + title + ".html")
//}
//companion object {
//    val BASE_URL = "http://www.turtlecare.net/"
//}


//val allBooks = setOf("Macbeth", "Romeo and Juliet", "Hamlet", "A Midsummer Night's Dream")
//val library = mapOf("Shakespeare" to allBooks)
//println(library.any { it.value.contains("Hamlet") })
//
//val moreBooks = mutableMapOf<String, String>("Wilhelm Tell" to "Schiller")
//moreBooks.getOrPut("Jungle Book") { "Kipling" }
//moreBooks.getOrPut("Hamlet") { "Shakespeare" }
//println(moreBooks)


//// code to reverse a list
//fun main(args: Array<String>) {
//    val testList = listOf(11,12,13,14,15,16,17,18,19,20)
//
//    println(testList.reversed())
//}
//fun reverseList(list: List<Int>): List<Int> {
//    val result = mutableListOf<Int>()
//    for (i in 0..list.size-1) {
//        result.add(list[list.size-i-1])
//    }
//    return result
//}
//fun reverseListAgain(list: List<Int>): List<Int> {
//    val result = mutableListOf<Int>()
//    for (i in list.size -1 downTo 0) {
//        result.add(list.get(i))
//    }
//    return result
//}




//class Book(val title: String, val author: String, val year: Int) {
//    fun getTitleAuthor(): Pair<String, String> {
//        return (title to author)
//    }
//    fun getTitleAuthorYear(): Triple<String, String, Int> {
//        return Triple(title, author, year)
//    }
//}
//fun main(args: Array<String>) {
//    val book = Book("Romeon and Juliet", "William Shakespeare", 1597)
//    val bookTitleAuthor = book.getTitleAuthor()
//    val bookTitleAuthorYear = book.getTitleAuthorYear()
//    println("Here is your book ${bookTitleAuthor.first} by ${bookTitleAuthor.second}")
//    println("Here is your book ${bookTitleAuthorYear.first} " +
//            "by ${bookTitleAuthorYear.second} written in ${bookTitleAuthorYear.third}")
//}


//enum class Color(val rgb: Int) {
//    RED(0xFF0000), GREEN(0x00FF00), BLUE(0x0000FF);
//}
//// Making Spice a sealed class helps keep all the spices together in one file.
//interface SpiceColor {
//    val color: Color
//}
//
//object YellowSpiceColor : SpiceColor {
//    override val color = Color.YELLOW
//}


//// TYPES OF CLASSES
//// use "object" instead of "class" if there should only be one
//object MobyDickWhale {
//    val author = "Herman Melvill"
//    fun jump() {
//        // ...
//    }
//}
//enum class Color(val rgb: Int) {
//    RED(0xFF0000),
//    GREEN(0x00FF00),
//    BLUE(0x0000FF)
//}
//sealed class Seal
//class SeaLion: Seal()
//class Walrus: Seal()
//fun matchSeal(seal: Seal): String {
//    return when(seal) {
//        is Walrus -> "walrus"
//        is SeaLion -> "sea lion"
//    }
//}



//data class SpiceContainer(var spice: Spice) {
//    val label = spice.name
//}
//val spiceCabinet = listOf(SpiceContainer(Curry("Yellow Curry", "mild")),
//        SpiceContainer(Curry("Red Curry", "medium")),
//        SpiceContainer(Curry("Green Curry", "spicy")))
//for(element in spiceCabinet) println(element.label)


//fun main (args: Array<String>) {
//    delegate()
//}
//
//fun delegate() {
//    val pleco = Plecostomus()
//    println("Fish has has color ${pleco.color}")
//    pleco.eat()
//}
//
//interface FishAction {
//    fun eat()
//}
//
//interface FishColor {
//    val color: String
//}
//
//object GoldColor : FishColor {
//    override val color = "gold"
//}
//
//class PrintingFishAction(val food: String) : FishAction {
//    override fun eat() {
//        println(food)
//    }
//}
//
//class Plecostomus (fishColor: FishColor = GoldColor):
//        FishAction by PrintingFishAction("eat a lot of algae"),
//        FishColor by fishColor
//
//abstract class Spice(val name: String, val spiciness: String = "mild", color: SpiceColor) : SpiceColor by color {
//    abstract fun prepareSpice()
//}
//
//class Curry(name: String, spiciness: String,
//            color: SpiceColor = YellowSpiceColor) : Spice(name, spiciness, color), Grinder {
//    override fun grind() {
//    }
//
//    override fun prepareSpice() {
//        grind()
//    }
//}
//
//interface Grinder {
//    fun grind()
//}
//
//interface SpiceColor {
//    val color: String
//}
//
//object YellowSpiceColor : SpiceColor {
//    override val color = "Yellow"
//}


//        open class Book(val title: String, val author: String) {
//            private var currentPage = 1
//            open fun readPage() {
//                currentPage++
//            }
//        }
//        class eBook(title: String, author: String, var format: String = "text") : Book(title, author) {
//            private var wordsRead = 0
//            override fun readPage() {
//                wordsRead = wordsRead + 250
//            }
//        }


//        class Spice(val name: String, val spiciness: String = "mild") {
//
//            private val heat: Int
//                get() {
//                    return when (spiciness) {
//                        "mild" -> 1
//                        "medium" -> 3
//                        "spicy" -> 5
//                        "very spicy" -> 7
//                        "extremely spicy" -> 10
//                        else -> 0
//                    }
//                }
//        }
//        val spices1 = listOf(
//                Spice("curry", "mild"),
//                Spice("pepper", "medium"),
//                Spice("cayenne", "spicy"),
//                Spice("ginger", "mild"),
//                Spice("red curry", "medium"),
//                Spice("green curry", "mild"),
//                Spice("hot pepper", "extremely spicy")
//        )
//        val spice = Spice("cayenne", spiciness = "spicy")
//        val spicelist = spices1.filter {it.heat < 5}
//        fun makeSalt() = Spice("Salt")


        //class SimpleSpice(){
        //    val name = "curry"
        //    val spiciness = "mild"
        //    val heat: Int
        //        get() {return 5 }
        //}
        //val simpleSpice = SimpleSpice()
        //println("${simpleSpice.name} ${simpleSpice.heat}")


    //        val spices = listOf("curry", "pepper", "cayenne", "ginger", "red curry", "green curry", "red pepper" )
    //        // Sorting curries by string length
    //        spices.filter { it.contains("curry") }.sortedBy { it.length }
    //        // Filtering by those that start with 'c' and end with 'e'
    //        spices.filter{it.startsWith('c')}.filter{it.endsWith('e')}
    //        spices.take(3).filter{it.startsWith('c')}


//        // What is the difference between
//        val random1 = random()
//        val random2 = {random()}
//        // random1 has a value assigned at compile time, and the value never changes when the variable is accessed.
//        // random2 has a lambda assigned at compile time, and the lambda is executed every time the variable is referenced, returning a different value.


//        val rollDice = { Random().nextInt(12) + 1}
//        val rollDice = { sides: Int ->
//            Random().nextInt(sides) + 1
//        }
//        val rollDice0 = { sides: Int ->
//            if (sides == 0) 0
//            else Random().nextInt(sides) + 1
//        }
//        val rollDice2: (Int) -> Int = { sides ->
//            if (sides == 0) 0
//            else Random().nextInt(sides) + 1
//        }


//        gamePlay(rollDice2(4))
//        fun gamePlay(diceRoll: Int){
//            // do something with the dice roll
//            println(diceRoll)
//        }


        //fun dayOfWeek() {
        //    println("What day is it today?")
        //    val day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
        //    println( when (day) {
        //        1 -> "Sunday"
        //        2 -> "Monday"
        //        3 -> "Tuesday"
        //        4 -> "Wednesday"
        //        5 -> "Thursday"
        //        6 -> "Friday"
        //        7 -> "Saturday"
        //        else -> "Error"
        //    })
        //}
        //fun main(args: Array<String>) {
        //    dayOfWeek()
        //}


        //Create a main() function that takes an argument representing the time in 24-hour format (values between and including 0 -> 23).
        //In the main() function, check if the time is before midday (<12), then print "Good morning, Kotlin"; otherwise, print "Good night, Kotlin".
        //fun main(args: Array<String>) {
        ////    if (args[0].toInt() < 12) println("Good morning, Kotlin")
        ////    else println("Good night, Kotlin" )
        //    println("${if (args[0].toInt() < 12) "Good morning, Kotlin" else "Good night, Kotlin"}")
        //}


        //Create a program with a function that returns a fortune cookie message that you can print.
        //
        //Create a main() function.
        //From the main() function, call a function, getFortuneCookie(), that returns a String.
        //Create a getFortuneCookie() function that takes no arguments and returns a String.
        //In the body of getFortuneCookie(), create a list of fortunes. Here are some ideas:
        //
        //"You will have a great day!"
        //"Things will go well for you today."
        //"Enjoy a wonderful day of success."
        //"Be humble and all will turn out well."
        //"Today is a good day for exercising restraint."
        //"Take it easy and enjoy life!"
        //"Treasure your friends because they are your greatest fortune."
        //Below the list, print: "Enter your birthday: "
        //
        //Hint: Use print(), not println()
        //Create a variable, birthday.
        //Read the user's input form the standard input and assign it to birthday. If there is no valid input, set birthday to 1.
        //Hint: Use readLine() to read a line of input (completed with Enter) as a String.
        //Hint: In Kotlin, you can use toIntOrNull() to convert a number as a String to an Integer numeric. If the user enters "", toIntOrNull returns null.
        //Hint: Check for null using the ? operator and use the ?: operator to handle the null case.
        //Divide the birthday by the number of fortunes, and use the remainder as the index for the fortune to return.
        //Return the fortune.
        //In main(), print: "Your fortune is: ", followed by the fortune string.
        //Extra practice:
        //Use a for loop to run the program 10 times, or until the "Take it easy" fortune has been selected.
        //
        //fun main(args: Array<String>) {
        //    println("\nYour fortune is: ${getFortuneCookie()}")
        //}
        //fun getFortuneCookie() : String {
        //    val fortunes = listOf( "You will have a great day!",
        //        "Things will go well for you today.",
        //        "Enjoy a wonderful day of success.",
        //        "Be humble and all will turn out well.",
        //        "Today is a good day for exercising restraint.",
        //        "Take it easy and enjoy life!",
        //        "Treasure your friends, because they are your greatest fortune.")
        //    print("\nEnter your birthday: ")
        //    val birthday = readLine()?.toIntOrNull() ?: 1
        //    return fortunes[birthday.rem(fortunes.size)]
        //}
        //
        //fun main(args: Array<String>) {
        //    var fortune: String
        //    for (i in 1..10) {
        //        fortune = getFortuneCookie()
        //        println("\nYour fortune is: $fortune")
        //        if (fortune.contains("Take it easy")) break
        //    }
        //}



        //fun main(args: Array<String>) {
        //    var fortune: String
        //    for (i in 1..10) {
        //        fortune = getFortune(getBirthday())
        //        println("\nYour fortune is: $fortune")
        //        if (fortune.contains("Take it easy")) break;
        //    }
        //}
        //fun getBirthday(): Int {
        //    print("\nEnter your birthday: ")
        //    return readLine()?.toIntOrNull() ?: 1
        //}
        //fun getFortune(birthday: Int): String {
        //    val fortunes = listOf("You will have a great day!",
        //    "Things will go well for you today.",
        //    "Enjoy a wonderful day of success.",
        //    "Be humble and all will turn out well.",
        //    "Today is a good day for exercising restraint.",
        //    "Take it easy and enjoy life!",
        //    "Treasure your friends, because they are your greatest fortune.")
        //    val index = when (birthday) {
        //        in 1..7 -> 4
        //        28, 31 -> 2
        //        else -> birthday.rem(fortunes.size)
        //    }
        //    return fortunes[index]
        //}


//        fun whatShouldIDoToday(mood: String, weather: String = "sunny", temperature: Int = 24): String {
//            return when {
//                mood == "happy" && weather == "Sunny" -> "Go for a walk"
//                mood == "sad" && weather == "rainy" && temperature == 0 -> "Stay in bed"
//                temperature > 35 -> "Go swimming"
//                else -> "Stay home and read"
//            }
//        }
//        fun isVeryHot (temperature: Int) = temperature > 35
//        fun isSadRainyCold (mood: String, weather: String, temperature: Int) =
//                mood == "sad" && weather == "rainy" && temperature == 0
//        fun isHappySunny (mood: String, weather: String) = mood == "happy" && weather == "sunny"
//        fun whatShouldIDoToday(mood: String, weather: String = "sunny", temperature: Int = 24) : String {
//            return when {
//                isVeryHot(temperature) -> "go swimming"
//                isSadRainyCold(mood, weather, temperature) -> "stay in bed"
//                isHappySunny(mood, weather) -> "go for a walk"
//                else -> "Stay home and read."
//            }
//        }
//        var fortune: String = ""
//        repeat (10) {
//            fortune = getFortune(getBirthday())
//            println("\nYour fortune is: $fortune")
//            if (fortune.contains("Take it easy")) break;
//        }
//        Log.i("Test1", "$whatShouldIDoToday('happy', 'sunny')")
//        Log.i("Test1", "$whatShouldIDoToday('sad')")
//        Log.i("Test1", "How do you feel?")
//        Log.i("Test1", "$whatShouldIDoToday(readline()!!)")


