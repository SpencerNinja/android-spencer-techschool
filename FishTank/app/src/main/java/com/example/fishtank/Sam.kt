package com.example.fishtank

// SAM = Single Abstract Method
// an interface with one method on it
interface Runnable {
    fun run()
}

interface Callable<T> {
    fun call(): T
}

//// take a SAM as a parameter
//package com.example.Aquarium5.Aquarium5
//public class JavaRun {
//    public static void runNow(Runnable runnable) {
//        runnable.run()
//    }
//}


//fun example() {
//    val runnable = object: Runnable {
//        override fun run() {
//            println("I'm a runnable")
//        }
//    }
//    JavaRun.runNow()
//}


//fun example() {
//    JavaRun.runNow {
//        println("Passing a lambda as a runnable")
//    }
//}
