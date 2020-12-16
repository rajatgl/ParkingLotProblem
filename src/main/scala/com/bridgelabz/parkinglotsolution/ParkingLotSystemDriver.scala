package com.bridgelabz.parkinglotsolution

import com.bridgelabz.parkinglotsolution.observers.{AirportPersonal, Driver, ParkingAttendant, ParkingLotOwner}

/**
 * Created on 12/9/2020.
 * Class: RealWorld.scala
 * Author: Rajat G.L.
 */
object ParkingLotSystemDriver extends App {

  var running: Boolean = true
  println("Hey Sanjay! How many parking lots do you own?")
  var errorLess: Boolean = false
  while (!errorLess) {
    try {
      ParkingLotManager.addParkingLot(scala.io.StdIn.readInt())
      errorLess = true
    }
    catch {
      case _: Exception =>
        println("Hey Sanjay! We hit an error. How many parking lots do you own?")
    }
  }
  try {
    while (running) {
      println("Welcome to Real World Parking Lot. Enter:\n1. to Park\n2. to UnPark\n3. to get positions of all White Cars\n4. to get details of all Blue Toyotas\n5. to get details of all BMWs\n6. to get details of all cars parking within 30 seconds\n7. to get details of all handicap small cars\n8. to Quit")
      val choice: Int = scala.io.StdIn.readInt()
      choice match {
        case 1 =>
          print("Enter the name of the parking attendant: ")
          val driver = new ParkingAttendant(scala.io.StdIn.readLine())
          print("Enter the color of the vehicle: ")
          val color = scala.io.StdIn.readLine().trim
          print("Enter the make of the car: ")
          val make = scala.io.StdIn.readLine().trim
          println("Is the vehicle large? Press Y for yes, anything else for no.")
          val isLarge = (scala.io.StdIn.readChar() == 'Y')

          println("Are you handicap? Press Y for yes, anything else for no.")
          if (scala.io.StdIn.readChar() == 'Y') {
            driver.setVehicle(color, make, isHandicap = true, isLarge = isLarge)
            ParkingLotManager.handicappedPark(driver)
          } else {
            driver.setVehicle(color, make, isLarge = isLarge)
            ParkingLotManager.park(driver)
          }

        case 2 =>
          print("Enter your vehicle's number plate: ")
          val numberPlate = scala.io.StdIn.readLine().toUpperCase()
          print("Enter Lot Number: ")
          val parkingLotNumber = scala.io.StdIn.readInt()
          ParkingLotManager.depart(numberPlate, parkingLotNumber)

        case 3 =>
          ParkingLotManager.getAllCars("White")

        case 4 =>
          ParkingLotManager.getAllCars("Blue", "Toyota")

        case 5 =>
          ParkingLotManager.getAllCarsWithMake("BMW")

        case 6 =>
          ParkingLotManager.getAllCars(30)

        case 7 =>
          ParkingLotManager.getAllHandicapCars(false)
          
        case 8 =>
          running = false

        case _ =>
          println("Invalid choice")
      }
    }
  } catch {
    case e: NumberFormatException => println("Invalid Input")
  }
}

