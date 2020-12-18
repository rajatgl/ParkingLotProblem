package com.bridgelabz.parkinglotsolution.observers

import java.util.Date

import com.bridgelabz.parkinglotsolution.Vehicle
import com.bridgelabz.parkinglotsolution.design.{Message, Observer}

class Driver(name: String) extends Observer {

  var vehicle: Vehicle = null

  /**
   *
   * @return the Name of the driver
   */
  def getName(): String = {
    name
  }

  /**
   *
   * @param color of the vehicle
   * @param make of the vehicle
   * @param isHandicap to check if the driver is handicap
   * @param isLarge to check if the vehicle is large
   */
  def setVehicle(color: String, make:String, numberPlate: String, isHandicap: Boolean = false, isLarge: Boolean = false): Unit = {

    vehicle = new Vehicle(numberPlate, new Date().getTime, isLarge, color, make, isHandicap)
  }

  /**
   *
   * @param message to updated the driver regarding parking status.
   */
  override def update(message: Message): Unit = {
    println("Driver with vehicle " + vehicle.getNumberPlate() + ": " + message.getMessageContent)
  }
}
