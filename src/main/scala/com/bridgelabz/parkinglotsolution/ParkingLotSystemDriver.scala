package com.bridgelabz.parkinglotsolution

import com.bridgelabz.parkinglotsolution.observers.{AirportPersonal, Driver, ParkingAttendant, ParkingLotOwner}
import com.typesafe.scalalogging.Logger

/**
 * Created on 12/9/2020.
 * Class: RealWorld.scala
 * Author: Rajat G.L.
 */

// Driver class: does not need checks
// $COVERAGE-OFF$
// scalastyle:off

object ParkingLotSystemDriver extends App {

  var running: Boolean = true
  private val logger = Logger("ParkingLotSystemDriver")

  println("Hey Sanjay! How many parking lots do you own?")

  var errorLess: Boolean = false
  while (!errorLess) {
    try {
      ParkingLotManager.addParkingLot(scala.io.StdIn.readInt())
      errorLess = true
    }
    catch {
      case e: Exception =>
        logger.error(e.getMessage)
        println("Hey Sanjay! We hit an error. How many parking lots do you own?")
    }
  }

  while (running) {
    try {
      println(
        """Welcome to Real World Parking Lot.
          |Enter:
          |1. to Park
          |2. to UnPark
          |3. to get positions of all White Cars
          |4. to get details of all Blue Toyotas
          |5. to get details of all BMWs
          |6. to get details of all cars parking within 30 seconds
          |7. to get details of all handicap small cars
          |8. to get details of all cars
          |9. to Quit""".stripMargin)

      val choice: Int = scala.io.StdIn.readInt()
      choice match {
        case 1 =>
          //Enter the required details to park
          print("Enter the name of the parking attendant: ")
          val driver = new ParkingAttendant(scala.io.StdIn.readLine())
          print("Enter the color of the vehicle: ")
          val color = scala.io.StdIn.readLine().trim
          print("Enter the make of the car: ")
          val make = scala.io.StdIn.readLine().trim
          println("Is the vehicle large? Press Y for yes, anything else for no.")
          val isLarge = (scala.io.StdIn.readChar() == 'Y')
          var runningInner: Boolean = true
          var numberPlate = ""
          while (runningInner) {
            print("Enter the number plate of the vehicle: ")
            numberPlate = scala.io.StdIn.readLine().toUpperCase()
            if (numberPlate != "") {
              runningInner = false
            } else {
              println("Invalid Input")
            }
          }

          println("Are you handicap? Press Y for yes, anything else for no.")
          if (scala.io.StdIn.readChar() == 'Y') {
            driver.setVehicle(color, make, numberPlate, isHandicap = true, isLarge = isLarge)
            ParkingLotManager.handicappedPark(driver)
          } else {
            driver.setVehicle(color, make, numberPlate, isLarge = isLarge)
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
          ParkingLotManager.getAllCars()

        case 9 =>
          running = false

        case _ =>
          println("Invalid choice")
      }
    }
    catch {
      case e: NumberFormatException =>
        logger.error(e.getMessage)
        println("Invalid Input")
    }
  }
}

