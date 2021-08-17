class DateRange(val start: MyDate, val end: MyDate) : Iterable<MyDate> {
    // https://www.techiedelight.com/differences-between-iterator-and-iterable-in-java/
    override fun iterator(): Iterator<MyDate> {
        return object : Iterator<MyDate> {
            // start date saved as current
            var current: MyDate = start

            override fun next(): MyDate {
                if (!hasNext()) throw NoSuchElementException()
                // set result equal to current
                val result = current
                // call function to find next date and reassign current
                current = current.followingDate()
                // return the next date
                return result
            }

            // returns true if current date is < or == to end
            override fun hasNext(): Boolean = current <= end
        }
    }
}


fun iterateOverDateRange(firstDate: MyDate, secondDate: MyDate, handler: (MyDate) -> Unit) {
    for (date in firstDate..secondDate) {
        handler(date)
    }
}