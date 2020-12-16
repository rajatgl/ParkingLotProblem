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
    for(i <- 0 until count){
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

  def getAllWhiteCars(): Unit = {
    val listOfList: util.ArrayList[util.ArrayList[Int]] = new util.ArrayList[util.ArrayList[Int]]()
    for(parkingLotIndex <- 0 until parkingLots.size()){
      listOfList.add(parkingLots.get(parkingLotIndex).getAllWhiteCars())
    }

    var message:String = ""
    for(rowIndex <- 0 until listOfList.size()){
      message += "Parking Lot Number: " + rowIndex + " => {"
      for(colIndex <- 0 until listOfList.get(rowIndex).size()){
        if(colIndex != listOfList.get(rowIndex).size() - 1)
          message += colIndex + ", "
        else
          message += colIndex
      }
      message += "}, "
    }
    message = message.substring(0, message.length - 2)
    PoliceDepartment.update(new Message(message))
  }
}
