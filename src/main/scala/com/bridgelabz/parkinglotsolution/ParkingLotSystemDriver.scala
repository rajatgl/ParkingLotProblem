package com.bridgelabz.parkinglotsolution

import java.util

import com.bridgelabz.parkinglotsolution.exception.ParkingLotException
import com.bridgelabz.parkinglotsolution.observers.{AirportPersonal, Driver, ParkingAttendant, ParkingLotOwner}
import jdk.nashorn.api.tree.ArrayLiteralTree


/**
 * Created on 12/9/2020.
 * Class: RealWorld.scala
 * Author: Rajat G.L.
 */
object ParkingLotSystemDriver extends App {

  var running: Boolean = true
  ParkingLotManager.addParkingLot()
  ParkingLotManager.addParkingLot()
  try {
    while (running) {
      println("Welcome to Real World Parking Lot. Enter:\n1. to Park\n2. to UnPark\n3. to Quit")
      var choice: Int = scala.io.StdIn.readInt()
      choice match {
        case 1 =>
          val driver = new ParkingAttendant()
          println("Is the vehicle large? Press Y for yes, anything else for no.")
          if(scala.io.StdIn.readChar() == 'Y')
            driver.setVehicle(isLarge = true)
          else
            driver.setVehicle()

          println("Are you handicap? Press Y for yes, anything else for no.")
          if(scala.io.StdIn.readChar() == 'Y')
            ParkingLotManager.handicappedPark(driver)
          else
            ParkingLotManager.park(driver)

        case 2 =>
          print("Enter your vehicle's number plate: ")
          val numberPlate = scala.io.StdIn.readLine().toUpperCase()
          print("Enter Lot Number: ")
          val parkingLotNumber = scala.io.StdIn.readInt()
          ParkingLotManager.depart(numberPlate, parkingLotNumber)

        case 3 =>
          running = false
        case _ =>
          println("Invalid choice")
          running = true
      }
    }
  } catch {
    case e: NumberFormatException => println("Invalid Input")
  }
}

