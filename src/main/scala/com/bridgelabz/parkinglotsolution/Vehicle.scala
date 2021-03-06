package com.bridgelabz.parkinglotsolution

/**
 * Vehicle class to generate Vehicle objects
 */

class Vehicle(numberPlate: String, arrivalTime: Long, isLarge: Boolean, color: String, make: String, isHandicap: Boolean) {
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
  def getMake(): String ={
    make
  }
  def getIsHandicap(): Boolean = {
    isHandicap
  }
}
