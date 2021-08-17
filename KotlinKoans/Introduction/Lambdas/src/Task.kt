fun containsEven(collection: Collection<Int>): Boolean =
        collection.any { num: Int -> num % 2 == 0 }
