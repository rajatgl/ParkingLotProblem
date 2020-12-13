package com.bridgelabz.parkinglotsolution

/**
 * Vehicle class to generate Vehicle objects
 */

class Vehicle(numberPlate: String, arrivalTime: Long ) {
  def getNumberPlate(): String = {
    numberPlate
  }
  def getArrivalTime(): Long = {
    arrivalTime
  }
}
