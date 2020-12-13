package com.bridgelabz.parkinglotsolution

import com.bridgelabz.parkinglotsolution.observers.{AirportPersonal, Driver, ParkingLotOwner}


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
        val driver = new Driver
        driver.vehicle = new Vehicle()
        lot.attach(driver)
        lot.park(driver)

      case 2 =>
        print("Enter the assigned ParkingSpot Number: ")
        var parkingSpot: Int = scala.io.StdIn.readInt()
        lot.depart(parkingSpot)

      case 3 =>
        running = false
    }
  }
}
