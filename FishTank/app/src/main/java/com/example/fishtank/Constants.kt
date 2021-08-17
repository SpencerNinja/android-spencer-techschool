package com.example.fishtank


// the value that is assigned can be determined at execution
val number = 5

// the value is always determined at compile time
const val rocks = 3
const val  num = 5

// val can call and execute functions, but const cannot
fun complexFunctionCall() {}
val result = complexFunctionCall()

const val CONSTANT = "top-level constant"
object Constants {
    const val CONSTANTS2 = "object constant"
}
val foo = Constants.CONSTANTS2

class MyClass {
    companion object {
        const val CONSTANT3 = "constant inside companion"
    }
}
