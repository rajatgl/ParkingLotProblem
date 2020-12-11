package com.bridgelabz.parkinglotsolution

/**
 * Created on 12/9/2020.
 * Class: ParkingLot.scala
 * Author: Rajat G.L.
 */
class ParkingLot {

  var parkingLotSize: Int = 100
  var parkingLot: Array[Vehicle] = Array.ofDim[Vehicle](parkingLotSize)
  private var currentlyParked: Int = 0

  def ParkingLot(): Unit ={
    init
  }

  def init:Unit = {
    for(index <- 0 to parkingLotSize)
      parkingLot(index) = null
  }

  /**
   * Avoid overhead of traversing the whole array to find out number of parking spots occupied
   * @return number of parking spots occupied
   */
  def getCurrentlyParked: Int = {
    currentlyParked
  }

  /**
   *
   * @return -1 if cannot park (or lot is full) or return valid value (between 1 and parkingLotSize) if parked
   */
  def park(vehicle: Vehicle): Boolean = {
    if (currentlyParked == parkingLotSize)
      false
    else {
      for (parkingSpot <- 0 until parkingLotSize) {
        if (parkingLot(parkingSpot) == null) {
          parkingLot(parkingSpot) = vehicle
          currentlyParked += 1
          println("Parked at ParkingSpot Number:"+parkingSpot)
          return true
        }
      }
      false
    }
  }
}
