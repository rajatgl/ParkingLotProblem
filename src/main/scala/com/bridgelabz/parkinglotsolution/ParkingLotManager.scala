package com.bridgelabz.parkinglotsolution

import java.util

import com.bridgelabz.parkinglotsolution.observers.{AirportPersonal, Driver, ParkingLotOwner}

/**
 * Created on 12/16/2020.
 * Class: ParkingLotManager.scala
 * Author: Rajat G.L.
 */
object ParkingLotManager {
  val parkingLots =  new util.ArrayList[ParkingLot]
  def addParkingLot(): Unit ={
    val parkingLot = new ParkingLot
    parkingLot.index = parkingLots.size()
    parkingLot.attach(ParkingLotOwner)
    parkingLot.attach(new AirportPersonal)
    parkingLots.add(parkingLot)
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
  
  def depart(numberPlate: String, parkingLotIndex: Int): Unit ={
    parkingLots.get(parkingLotIndex).depart(numberPlate)
  }
}
