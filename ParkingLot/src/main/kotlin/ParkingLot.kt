import kotlin.random.Random

/**
 * You’ve been tasked with developing a simple management system for a small car lot. It will need to be able to handle
 * at least cars, trucks and motorcycles. You’ll need to track at least the year, make and color of each vehicle. There
 * should also be a way to store any of them in a garage for between 1 and 8 weeks. There also needs to be a way of
 * printing out the vehicle information and days it will be stored. As an extra challenge you should also implement a
 * way to calculate the fee per vehicle type. (Flat fees are: $15 - Motorcycle, $18 - Car, $25 - Truck) per day. This
 * should appear in the formatted print out.
 *
 * 1: Create a project in IntelliJ
 * 2: Create a file named CarLot (This will be similar to the List Manager that we did)
 * 3: Copy these steps to the file in a comment as your pseudocode
 * 4: Write an interface called ‘Vehicle’ which has properties for id, year, make, color
 * 5: Write data classes for Car, Truck and Motorcycle.
 * 6: Have each of these classes implement ‘Vehicle’
 * 7: You may want to give each class property an initial value
 * 8: Each class will have a private constructor variable called style; a VehicleType enum
 * 9: This enum will have coupe, sedan, suv, pickup, diesel, semi, dirt, bullet, road
 * 10: If it makes more sense, you can write a nested enum for this instead
 * 11: Write a generic class called ‘Garage’ which has a type parameter constrained to ‘Vehicle’
 * 12: It’s constructor takes an array of vehicles
 * 13: This class has a method ‘store’ which takes a vehicle and number of weeks
 * 14: It also takes a completion which will give us back how many days it’s to be stored
 * 15: Write at least one more useful method for Garage. Can be generic or non
 * 16: Create an instance of your Garage
 * 17: Create an array of different vehicles with different makes and years
 * 18: Hand this array of vehicles off to your Garage to store them for between 1-8 weeks
 * 19: Using our completion, format a print statement that tells us the color, year, make and number of days each
 *     vehicle is being stored for. Optionally, print out the total fee as well.
 * 20: Look over your code and see if there is anything that could be improved by refactoring. Did you write clear
 *     comments so you and the next person to come along will be able to understand how your code works?
 */

// 4: Write an interface called ‘Vehicle’ which has properties for id, year, make, color
interface Vehicle {
    var id: Int
    var year: Int
    var make: String
    var color: String
}

// 5: Write data classes for Car, Truck and Motorcycle.
// 6: Have each of these classes implement ‘Vehicle’
// 7: You may want to give each class property an initial value
// 8: Each class will have a private constructor variable called style; a VehicleType enum
data class Car(
    private var style: VehicleType.Car,
    override var id: Int,
    override var year: Int,
    override var make: String,
    override var color: String) : Vehicle {}
data class Truck(
    private var style: VehicleType.Truck,
    override var id: Int,
    override var year: Int,
    override var make: String,
    override var color: String) : Vehicle {}
data class Motorcycle(
    private var style: VehicleType.Motorcycle,
    override var id: Int,
    override var year: Int,
    override var make: String,
    override var color: String) : Vehicle {}

// 9: This enum will have coupe, sedan, suv, pickup, diesel, semi, dirt, bullet, road
// 10: If it makes more sense, you can write a nested enum for this instead
enum class VehicleType {
    ;
    enum class Car {
        coupe, sedan, suv
    }
    enum class Truck {
        pickup,
        diesel,
        semi
    }
    enum class Motorcycle {
        dirt,
        bullet,
        road
    }
}

// 11: Write a generic class called ‘Garage’ which has a type parameter constrained to ‘Vehicle’
// 12: Its constructor takes an array of vehicles
class Garage<T: Vehicle>(var listOfVehicles: MutableList<T> = mutableListOf()) {
    var numOfWeeks = -1
    // 13: This class has a method ‘store’ which takes a vehicle and number of weeks
    // 14: It also takes a completion which will give us back how many days it is to be stored
    fun store(vehicle: T, weeks: Int, completion: (numOfWeeks: Int) -> Unit) {
        numOfWeeks = weeks
        listOfVehicles.add(vehicle)
        completion(calcFee())
    }
    // 15: Write at least one more useful method for Garage. Can be generic or non
    fun remove(vehicle: Vehicle) {
        listOfVehicles.remove(vehicle)
    }
    fun calcDays(): Int {
        var numOfDays = numOfWeeks * 7
        return numOfDays
    }
    // Flat fees are: $15 - Motorcycle, $18 - Car, $25 - Truck per day
    fun calcFee(): Int {
        var cost: Int = -1
        var days: Int = calcDays()
        for (vehicle in VehicleType.values()) {
            if (vehicle == VehicleType.valueOf("motorcycle")) {
                cost = 15
            } else if (vehicle == VehicleType.valueOf("car")) {
                cost = 18
            } else if (vehicle == VehicleType.valueOf("truck")) {
                cost = 25
            } else {
                println("Error: Vehicle Type not available")
            }
        }
        var fee = cost * days
        return fee
    }
}

//fun listAllVehicles(garage: Garage<Vehicle>) {
//    for (vehicle in garage.listOfVehicles) {
//        println("The ${vehicle.color} ${vehicle.year} ${vehicle.make} has been in the lot for ${garage.calcDays()} days and owes ${garage.calcFee()}")
//    }
//}

fun main() {
    // 16: Create an instance of your Garage
    var garage = Garage<Vehicle>()

    // 17: Create an array of different vehicles with different makes and years
    var vehicle1 = Car(VehicleType.Car.sedan, 1, 2015, "Kia", "black")
    var vehicle2 = Car(VehicleType.Car.suv, 2, 2005, "Chevy", "white")
    var vehicle3 = Truck(VehicleType.Truck.pickup, 3, 2021, "Toyata", "silver")
    var vehicle4 = Motorcycle(VehicleType.Motorcycle.road, 4, 2017, "Honda", "black")

    var vehicles = arrayOf(vehicle1, vehicle2, vehicle3, vehicle4)

    // 18: Hand this array of vehicles off to your Garage to store them for between 1-8 weeks
    vehicles.forEach { vehicle ->
        val random = Random.nextInt(1,8)
        garage.store(vehicle, random) { week ->
            // 19: Using our completion, format a print statement that tells us the color, year, make and number of days each
            //     vehicle is being stored for. Optionally, print out the total fee as well.
            println("The ${vehicle.color} ${vehicle.year} ${vehicle.make} has been in the lot for ${garage.calcDays()} days and owes ${garage.calcFee()}")
        }
    }
}


// 20: Look over your code and see if there is anything that could be improved by refactoring.
// Did you write clear comments so you and the next person to come along will be able to understand how your code works?
