package com.bridgelabz.parkinglotsolution.observers

import java.util.Date

import com.bridgelabz.parkinglotsolution.Vehicle
import com.bridgelabz.parkinglotsolution.design.{Message, Observer}

import scala.util.matching.Regex

class Driver extends Observer {

  var vehicle: Vehicle = null

  def setVehicle(): Unit = {
    var running: Boolean = true
    while (running) {
      print("Enter the number plate of the vehicle: ")
      val numberPlate = scala.io.StdIn.readLine().toUpperCase()
      if (numberPlate != "") {
        vehicle = new Vehicle(numberPlate, new Date().getTime)
        running = false
      } else {
        println("Invalid Input")
        running = true
      }
    }
  }

  override def update(message: Message): Unit = {
    println(message.getMessageContent)
  }
}
