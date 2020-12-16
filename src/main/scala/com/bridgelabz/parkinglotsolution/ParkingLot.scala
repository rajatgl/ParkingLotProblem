package com.bridgelabz.parkinglotsolution

import java.text.SimpleDateFormat
import java.util
import java.util.Date

import com.bridgelabz.parkinglotsolution.design.{Message, Observer, Subject}
import com.bridgelabz.parkinglotsolution.observers.{AirportPersonal, Driver, ParkingAttendant, ParkingLotOwner}

/**
 * Created on 12/9/2020.
 * Class: ParkingLot.scala
 * Author: Rajat G.L.
 */
class ParkingLot extends Subject {

  var parkingLotSize: Int = 100
  var parkingLot: Array[Driver] = Array.ofDim[Driver](parkingLotSize)
  private var currentlyParked: Int = 0
  private val observers = new util.ArrayList[Observer]
  var index: Int = 0

  def ParkingLot(): Unit = {
    init
  }

  def init: Unit = {
    for (index <- 0 to parkingLotSize)
      parkingLot(index) = null
  }

  /**
   * Avoid overhead of traversing the whole array to find out number of parking spots occupied
   *
   * @return number of parking spots occupied
   */
  def getCurrentlyParked: Int = {
    currentlyParked
  }

  /**
   *
   * @return false if cannot park (or lot is full) or return true if parked
   */
  def park(driver: Driver, isHandicap: Boolean = false): Boolean = {
    if (isFull) {
      driver.update(new Message("Parking lot is full."))
      false
    }
    else if (!isHandicap && driver.getClass.toString.equals(classOf[ParkingAttendant].toString)) {
      var parkingOwnerChoice = ParkingLotOwner.getOpinionOnParkingSpot
      while (parkingOwnerChoice <= -1 || parkingOwnerChoice >= parkingLotSize || parkingLot(parkingOwnerChoice) != null) {
        println("Spots available from 0 to " + (parkingLotSize - 1) + ". In case you followed this, the parking space may be full. Try again.")
        parkingOwnerChoice = ParkingLotOwner.getOpinionOnParkingSpot
      }
      parkingLot(parkingOwnerChoice) = driver
      currentlyParked += 1
      ParkingLotOwner.update(new Message("Vehicle: " + driver.vehicle.getNumberPlate() + " arrived at: " + new SimpleDateFormat("dd MMM yy, HH:mm:ss").format(new Date(driver.vehicle.getArrivalTime()))))
      driver.update(new Message("Vehicle successfully parked at Spot Number : " + parkingOwnerChoice + ", Parking Lot Number: " + index))
      isFull
      true
    }
    else {
      for (parkingSpot <- 0 until parkingLotSize) {
        if (parkingLot(parkingSpot) == null) {
          parkingLot(parkingSpot) = driver
          ParkingLotOwner.update(new Message("Vehicle: " + driver.vehicle.getNumberPlate() + " arrived at: " + new SimpleDateFormat("dd MMM yy, HH:mm:ss").format(new Date(driver.vehicle.getArrivalTime()))))
          currentlyParked += 1
          driver.update(new Message("Vehicle successfully parked at Spot Number : " + parkingSpot + ", Parking Lot Number: " + index))
          isFull
          return true
        }
      }
      false
    }
  }

  def nearestFreeParkingSpot(): Int = {
    for (driverIndex <- 0 until parkingLotSize) {
      if (parkingLot(driverIndex) == null)
        return driverIndex
    }
    parkingLotSize
  }

  def depart(parkingSpot: Int): Boolean = {
    if (parkingLot(parkingSpot) == null) {
      return false
    }
    if (parkingLot(parkingSpot) != null) {
      if (currentlyParked == parkingLotSize) {
        notifyUpdate(new Message("Parking lot has space again, pull in the sign."), classOf[ParkingLotOwner.type].toString)
      }
      parkingLot(parkingSpot).update(new Message("Vehicle Departed successfully from Parking Spot Number: " + parkingSpot))
      parkingLot(parkingSpot) = null
      currentlyParked -= 1
      return true
    }
    false
  }

  def depart(numberPlate: String): Boolean = {
    for (driverIndex <- 0 until parkingLotSize) {
      if (parkingLot(driverIndex) != null && parkingLot(driverIndex).vehicle.getNumberPlate().equals(numberPlate)) {
        parkingLot(driverIndex).update(new Message("Vehicle Departed successfully from Parking Spot Number: " + driverIndex))
        parkingLot(driverIndex) = null
        currentlyParked -= 1
        return true
      }
    }
    false
  }

  def isFull: Boolean = {
    if (currentlyParked == parkingLotSize) {
      notifyUpdate(new Message("Parking lot is full. Put the sign."), classOf[ParkingLotOwner.type].toString)
      notifyUpdate(new Message("Attention: Parking lot is full. Call Security!"), classOf[AirportPersonal].toString)
      true
    }
    else
      false
  }

  def getAllCars(color: String): util.ArrayList[Int] = {
    val list: util.ArrayList[Int] = new util.ArrayList[Int]()
    for (driverIndex <- 0 until parkingLotSize) {
      if (parkingLot(driverIndex) != null && parkingLot(driverIndex).vehicle.getColor().toLowerCase().equals(color.toLowerCase())) {
        list.add(driverIndex)
      }
    }
    list
  }

  def getAllCars(seconds: Int): util.ArrayList[Int] = {
    val list: util.ArrayList[Int] = new util.ArrayList[Int]()
    for (driverIndex <- 0 until parkingLotSize) {
      if (parkingLot(driverIndex) != null && ((new Date().getTime/1000) - (parkingLot(driverIndex).vehicle.getArrivalTime()/1000)) <= seconds) {
        list.add(driverIndex)
      }
    }
    list
  }

  def getAllCarsWithMake(make: String): util.ArrayList[Int] = {
    val list: util.ArrayList[Int] = new util.ArrayList[Int]()
    for (driverIndex <- 0 until parkingLotSize) {
      if (parkingLot(driverIndex) != null && parkingLot(driverIndex).vehicle.getMake().toLowerCase().equals(make.toLowerCase())) {
        list.add(driverIndex)
      }
    }
    list
  }

  def getAllCars(color: String, make: String): util.ArrayList[Int] = {
    val list: util.ArrayList[Int] = new util.ArrayList[Int]()
    for (driverIndex <- 0 until parkingLotSize) {
      if (parkingLot(driverIndex) != null && parkingLot(driverIndex).vehicle.getColor().toLowerCase().equals(color.toLowerCase()) && parkingLot(driverIndex).vehicle.getMake().toLowerCase().equals(make.toLowerCase())) {
        list.add(driverIndex)
      }
    }
    list
  }

  def getAllHandicapCars(isLarge: Boolean = false): util.ArrayList[Int] = {
    val list: util.ArrayList[Int] = new util.ArrayList[Int]()
    for (driverIndex <- 0 until parkingLotSize) {
      if (parkingLot(driverIndex) != null && parkingLot(driverIndex).vehicle.getIsLarge().equals(isLarge) && parkingLot(driverIndex).vehicle.getIsHandicap()) {
        list.add(driverIndex)
      }
    }
    list
  }

  override def attach(observer: Observer): Unit = {
    observers.add(observer)
  }

  override def detach(observer: Observer): Unit = {
    observers.remove(observer)
  }

  override def notifyUpdate(message: Message): Unit = {
    for (index <- 0 until observers.size()) {
      observers.get(index).update(message)
    }
  }

  override def notifyUpdate(message: Message, classType: String): Unit = {
    for (index <- 0 until observers.size()) {
      if (observers.get(index).getClass.toString.equals(classType))
        observers.get(index).update(message)
    }
  }
}
