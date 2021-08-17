class Invokable {
    var numberOfInvocations: Int = 0
        private set

    operator fun invoke(): Invokable {
        // increment counter
        numberOfInvocations++
        // return object
        return this
    }
}

fun invokeTwice(invokable: Invokable) = invokable()()
