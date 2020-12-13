package com.bridgelabz.parkinglotsolution

import com.bridgelabz.parkinglotsolution.observers.{AirportPersonal, Driver, ParkingAttendant, ParkingLotOwner}


/**
 * Created on 12/9/2020.
 * Class: RealWorld.scala
 * Author: Rajat G.L.
 */
object ParkingLotSystemDriver extends App {

  var lot: ParkingLot = new ParkingLot()
  var running: Boolean = true
  lot.attach(ParkingLotOwner)
  lot.attach(new AirportPersonal)
  lot.parkingLotSize = 3

  while (running) {
    println("Welcome to Real World Parking Lot. Enter:\n1. to Park\n2. to UnPark\n3. to Quit")
    var choice: Int = scala.io.StdIn.readInt()
    choice match {
      case 1 =>
        val driver = new ParkingAttendant()
        driver.setVehicle()
        lot.attach(driver)
        lot.park(driver)

      case 2 =>
        print("Enter your vehicle's number plate: ")
        lot.depart(scala.io.StdIn.readLine().toUpperCase())

      case 3 =>
        running = false
    }
  }
}
