package com.bridgelabz.parkinglotsolution


/**
 * Created on 12/9/2020.
 * Class: RealWorld.scala
 * Author: Rajat G.L.
 */
object ParkingLotSystemDriver extends App {

  var lot: ParkingLot = new ParkingLot()
  var running: Boolean = true

  while (running) {
    println("Welcome to Real World Parking Lot. Enter:\n1. to Park\n2. to Quit")
    var choice: Int = scala.io.StdIn.readInt()

    choice match {
      case 1 =>
        lot.park(new Vehicle())

      case 2 =>
        running = false
    }
  }
}
