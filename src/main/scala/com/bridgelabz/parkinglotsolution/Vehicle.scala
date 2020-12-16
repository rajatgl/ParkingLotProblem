package com.bridgelabz.parkinglotsolution

/**
 * Vehicle class to generate Vehicle objects
 */

class Vehicle(numberPlate: String, arrivalTime: Long, isLarge: Boolean, color: String ) {
  def getNumberPlate(): String = {
    numberPlate
  }
  def getArrivalTime(): Long = {
    arrivalTime
  }
  def getIsLarge(): Boolean ={
    isLarge
  }
  def getColor(): String = {
    color
  }
}
