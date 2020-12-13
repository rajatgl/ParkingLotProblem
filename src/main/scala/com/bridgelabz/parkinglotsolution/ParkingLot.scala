package com.bridgelabz.parkinglotsolution

import java.util

import com.bridgelabz.parkinglotsolution.design.{Message, Observer, Subject}
import com.bridgelabz.parkinglotsolution.observers.{AirportPersonal, Driver, ParkingLotOwner}

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
  def park(driver: Driver): Boolean = {
    if (isFull) {
      driver.update(new Message("Parking lot is full."))
      false
    }
    else {
      for (parkingSpot <- 0 until parkingLotSize) {
        if (parkingLot(parkingSpot) == null) {
          parkingLot(parkingSpot) = driver
          currentlyParked += 1
          driver.update(new Message("Vehicle successfully parked at Spot Number : " + parkingSpot))
          isFull
          return true
        }
      }
      false
    }
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

  def isFull: Boolean = {
    if (currentlyParked == parkingLotSize) {
      notifyUpdate(new Message("Parking lot is full. Put the sign."), classOf[ParkingLotOwner.type].toString)
      notifyUpdate(new Message("Attention: Parking lot is full. Call Security!"), classOf[AirportPersonal].toString)
      true
    }
    else
      false
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
