import TimeInterval.*

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int)

// Supported intervals that might be added to dates:
enum class TimeInterval { DAY, WEEK, YEAR }

// add extension function plus() to MyDate taking TimeInterval as an argument
operator fun MyDate.plus(timeInterval: TimeInterval) =
        // find the date after amount number of days
        addTimeIntervals(timeInterval, 1)

// extra class needed to support TimeInterval.times function
class RepeatedTimeInterval(val timeInterval: TimeInterval, val number: Int)

// support adding several time intervals to a date
operator fun TimeInterval.times(number: Int) =
        RepeatedTimeInterval(this, number)

operator fun MyDate.plus(timeIntervals: RepeatedTimeInterval) =
        addTimeIntervals(timeIntervals.timeInterval, timeIntervals.number)

fun task1(today: MyDate): MyDate {
    return today + YEAR + WEEK
}

fun task2(today: MyDate): MyDate {
    // Implement date arithmetic
    return today + YEAR * 2 + WEEK * 3 + DAY * 5
}
