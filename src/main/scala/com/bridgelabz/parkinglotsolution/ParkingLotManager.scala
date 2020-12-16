package com.bridgelabz.parkinglotsolution

import java.util

import com.bridgelabz.parkinglotsolution.design.{Message, Observer, Subject}
import com.bridgelabz.parkinglotsolution.observers.{AirportPersonal, Driver, ParkingLotOwner, PoliceDepartment}

/**
 * Created on 12/16/2020.
 * Class: ParkingLotManager.scala
 * Author: Rajat G.L.
 */
object ParkingLotManager{
  val parkingLots =  new util.ArrayList[ParkingLot]
  def addParkingLot(): Unit ={
    val parkingLot = new ParkingLot
    parkingLot.index = parkingLots.size()
    parkingLot.attach(ParkingLotOwner)
    parkingLot.attach(new AirportPersonal)
    parkingLot.attach(PoliceDepartment)
    parkingLots.add(parkingLot)
  }

  def addParkingLot(count: Int): Unit = {
    for(_ <- 0 until count){
      addParkingLot()
    }
  }

  def park(driver: Driver): Unit ={
    var minOccupancy = parkingLots.get(0).getCurrentlyParked
    var parkingLotIndex: Int = 0
    for(parkingLotsIndex<-0 until parkingLots.size()) {
      {
        if(parkingLots.get(parkingLotsIndex).getCurrentlyParked < minOccupancy){
          minOccupancy = parkingLots.get(parkingLotsIndex).getCurrentlyParked
          parkingLotIndex = parkingLotsIndex
        }
      }
    }
    parkingLots.get(parkingLotIndex).park(driver)
    parkingLots.get(parkingLotIndex).attach(driver)
  }

  def handicappedPark(driver: Driver): Unit = {
    var minOccupancy = parkingLots.get(0).nearestFreeParkingSpot()
    var parkingLotIndex: Int = 0
    for(parkingLotsIndex<-0 until parkingLots.size()) {
      {
        if(parkingLots.get(parkingLotsIndex).nearestFreeParkingSpot() < minOccupancy){
          minOccupancy = parkingLots.get(parkingLotsIndex).nearestFreeParkingSpot()
          parkingLotIndex = parkingLotsIndex
        }
      }
    }
    parkingLots.get(parkingLotIndex).park(driver, isHandicap = true)
    parkingLots.get(parkingLotIndex).attach(driver)
  }

  def depart(numberPlate: String, parkingLotIndex: Int): Unit ={
    parkingLots.get(parkingLotIndex).depart(numberPlate)
  }

  def getAllCars(color: String): Unit = {
    val listOfList: util.ArrayList[util.ArrayList[Int]] = new util.ArrayList[util.ArrayList[Int]]()
    for(parkingLotIndex <- 0 until parkingLots.size()){
      listOfList.add(parkingLots.get(parkingLotIndex).getAllCars(color))
    }

    var message:String = ""
    for(parkingLotNumber <- 0 until listOfList.size()){
      message += "Parking Lot Number: " + parkingLotNumber + ": {"
      for(parkingLotSpot <- 0 until listOfList.get(parkingLotNumber).size()){
        if(parkingLotSpot != listOfList.get(parkingLotNumber).size() - 1)
          message += parkingLotSpot + ", "
        else
          message += parkingLotSpot
      }
      message += "}, "
    }
    message = message.substring(0, message.length - 2)
    PoliceDepartment.update(new Message(message))
  }

  def getAllCars(color: String, make: String): Unit = {
    val listOfList: util.ArrayList[util.ArrayList[Int]] = new util.ArrayList[util.ArrayList[Int]]()
    for(parkingLotIndex <- 0 until parkingLots.size()){
      listOfList.add(parkingLots.get(parkingLotIndex).getAllCars(color, make))
    }

    var message = ""
    for(parkingLotNumber <- 0 until listOfList.size()){
      message += "Parking Lot Number " + parkingLotNumber + ": {"
      for(parkingLotSpot <- 0 until listOfList.get(parkingLotNumber).size()){
        message += "Vehicle: {"
        val driver: Driver = parkingLots.get(parkingLotNumber).parkingLot(parkingLotSpot)
        message += "Number Plate: " + driver.vehicle.getNumberPlate() + ", "
        message += "PA Name: " + driver.getName() + ", "
        message += "Location: " + parkingLotSpot

        if(parkingLotSpot != listOfList.get(parkingLotNumber).size() - 1)
          message += "}, "
        else
          message += "}"
      }
      message += "}, "
    }
    message = message.substring(0, message.length - 2)
    PoliceDepartment.update(new Message(message))
  }
}
