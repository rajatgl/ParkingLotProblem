package com.bridgelabz.parkinglotsolution.observers

import java.util.Date

import com.bridgelabz.parkinglotsolution.Vehicle
import com.bridgelabz.parkinglotsolution.design.{Message, Observer}

class Driver(name: String) extends Observer {

  var vehicle: Vehicle = null

  def getName(): String = {
    name
  }

  def setVehicle(color: String, make:String, isLarge: Boolean = false): Unit = {
    var running: Boolean = true
    while (running) {
      print("Enter the number plate of the vehicle: ")
      val numberPlate = scala.io.StdIn.readLine().toUpperCase()
      if (numberPlate != "") {
        vehicle = new Vehicle(numberPlate, new Date().getTime, isLarge, color, make)
        running = false
      } else {
        println("Invalid Input")
        running = true
      }
    }
  }

  override def update(message: Message): Unit = {
    println("Driver with vehicle " + vehicle.getNumberPlate() + ": " + message.getMessageContent)
  }
}
