package com.bridgelabz.parkinglotsolution.observers

import java.util.Date

import com.bridgelabz.parkinglotsolution.Vehicle
import com.bridgelabz.parkinglotsolution.design.{Message, Observer}

class Driver extends Observer {

  var vehicle: Vehicle = null

  def setVehicle(): Unit = {

    print("Enter the number plate of the vehicle: ")
    vehicle = new Vehicle(scala.io.StdIn.readLine().toUpperCase(), new Date().getTime)
  }

  override def update(message: Message): Unit = {
    println(message.getMessageContent)
  }
}
