package com.bridgelabz.parkinglotsolution

import java.util

import com.bridgelabz.parkinglotsolution.design.Message
import com.bridgelabz.parkinglotsolution.observers.{AirportPersonal, Driver, ParkingLotOwner, PoliceDepartment}

/**
 * Created on 12/16/2020.
 * Class: ParkingLotManager.scala
 * Author: Rajat G.L.
 */
object ParkingLotManager {
  //ArrayList of Parking Lot
  var parkingLots = new util.ArrayList[ParkingLot]

  /**
   * To attach the observers and add the respective Parking Lot array into the List
   */
  def addParkingLot(): Unit = {
    val parkingLot = new ParkingLot
    parkingLot.index = parkingLots.size()
    parkingLot.attach(ParkingLotOwner)
    parkingLot.attach(new AirportPersonal)
    parkingLot.attach(PoliceDepartment)
    parkingLots.add(parkingLot)
  }

  /**
   *
   * @param count of Parking Lots owned by the Owner Observer
   */
  def addParkingLot(count: Int): Unit = {
    for (_ <- 0 until count) {
      addParkingLot()
    }
  }

  /**
   * To evenly park the vehicles into available Parking Lots
   * @param driver is the Driver object associated with the vehicle to be parked
   */
  def park(driver: Driver): Int = {
    var minOccupancy = parkingLots.get(0).getCurrentlyParked
    var parkingLotIndex: Int = 0
    for (parkingLotsIndex <- 0 until parkingLots.size()) {
      {
        if (parkingLots.get(parkingLotsIndex).getCurrentlyParked < minOccupancy) {
          minOccupancy = parkingLots.get(parkingLotsIndex).getCurrentlyParked
          parkingLotIndex = parkingLotsIndex
        }
      }
    }
    parkingLots.get(parkingLotIndex).park(driver)
    parkingLots.get(parkingLotIndex).attach(driver)

    parkingLotIndex
  }

  /**
   * to park the vehicle at nearest free space for the handicap drivers
   * @param driver Driver object associated with the vehicle
   */
  def handicappedPark(driver: Driver): Unit = {
    var minOccupancy = parkingLots.get(0).nearestFreeParkingSpot()
    var parkingLotIndex: Int = 0
    for (parkingLotsIndex <- 0 until parkingLots.size()) {
      {
        if (parkingLots.get(parkingLotsIndex).nearestFreeParkingSpot() < minOccupancy) {
          minOccupancy = parkingLots.get(parkingLotsIndex).nearestFreeParkingSpot()
          parkingLotIndex = parkingLotsIndex
        }
      }
    }
    parkingLots.get(parkingLotIndex).park(driver, isHandicap = true)
    parkingLots.get(parkingLotIndex).attach(driver)
  }

  /**
   * To Depart the already parked vehicle
   * @param numberPlate unique id for the vehicle
   * @param parkingLotIndex is the ParkingLot number where the vehicle is parked
   */
  def depart(numberPlate: String, parkingLotIndex: Int): Boolean = {
    parkingLots.get(parkingLotIndex).depart(numberPlate)
  }

  /**
   * To fetch the parking locations of the vehicles of required "color" and updated it to Police Dept
   * @param color of the vehicle to be investigated by Police Dept
   */
  def getAllCars(color: String): util.ArrayList[util.ArrayList[Int]] = {
    val listOfList: util.ArrayList[util.ArrayList[Int]] = new util.ArrayList[util.ArrayList[Int]]()
    for (parkingLotIndex <- 0 until parkingLots.size()) {
      listOfList.add(parkingLots.get(parkingLotIndex).getAllCars(color))
    }

    PoliceDepartment.update(new Message(getParkingAllotment(listOfList)))
    listOfList
  }

  /**
   * to update the details of all cars parked in the Lots to Police Dept
   */
  def getAllCars(): util.ArrayList[util.ArrayList[Int]] = {
    val listOfList: util.ArrayList[util.ArrayList[Int]] = new util.ArrayList[util.ArrayList[Int]]()
    for (parkingLotIndex <- 0 until parkingLots.size()) {
      listOfList.add(parkingLots.get(parkingLotIndex).getAllCars())
    }

    PoliceDepartment.update(new Message(getParkingAllotment(listOfList)))
    listOfList
  }

  /**
   * to update the details of all cars of required color and make to Police Dept
   * @param color of the vehicle
   * @param make of the vehicle
   */
  def getAllCars(color: String, make: String): util.ArrayList[util.ArrayList[Int]] = {
    val listOfList: util.ArrayList[util.ArrayList[Int]] = new util.ArrayList[util.ArrayList[Int]]()
    for (parkingLotIndex <- 0 until parkingLots.size()) {
      listOfList.add(parkingLots.get(parkingLotIndex).getAllCars(color, make))
    }

    PoliceDepartment.update(new Message(getParkingAllotment(listOfList)))
    listOfList
  }

  /**
   * to update the details of the vehicles parked recently to Police Dept
   * @param seconds time limit to check for recently parked cars
   */
  def getAllCars(seconds: Int): util.ArrayList[util.ArrayList[Int]] = {
    val listOfList: util.ArrayList[util.ArrayList[Int]] = new util.ArrayList[util.ArrayList[Int]]()
    for (parkingLotIndex <- 0 until parkingLots.size()) {
      listOfList.add(parkingLots.get(parkingLotIndex).getAllCars(seconds))
    }

    PoliceDepartment.update(new Message(getParkingAllotment(listOfList)))
    listOfList
  }

  /**
   * to update the details of vehicles of particular make to Police Dept
   * @param make of the vehicle
   */
  def getAllCarsWithMake(make: String): util.ArrayList[util.ArrayList[Int]] = {
    val listOfList: util.ArrayList[util.ArrayList[Int]] = new util.ArrayList[util.ArrayList[Int]]()
    for (parkingLotIndex <- 0 until parkingLots.size()) {
      listOfList.add(parkingLots.get(parkingLotIndex).getAllCarsWithMake(make))
    }

    PoliceDepartment.update(new Message(getParkingAllotment(listOfList)))
    listOfList
  }

  /**
   * To update the details of all Handicap vehicles to Police Dept
   * @param isLarge checks of the vehicle is Large or Small
   */
  def getAllHandicapCars(isLarge: Boolean): util.ArrayList[util.ArrayList[Int]] = {
    val listOfList: util.ArrayList[util.ArrayList[Int]] = new util.ArrayList[util.ArrayList[Int]]()
    for (parkingLotIndex <- 0 until parkingLots.size()) {
      listOfList.add(parkingLots.get(parkingLotIndex).getAllHandicapCars(isLarge))
    }

    PoliceDepartment.update(new Message(getParkingAllotment(listOfList)))
    listOfList
  }

  /**
   * Formats the details of vehicles parked in Lots into readable format
   * @param listOfList of ParkingLots
   * @return
   */
  def getParkingAllotment(listOfList: util.ArrayList[util.ArrayList[Int]]): String = {

    var message = ""
    for (parkingLotNumber <- 0 until listOfList.size()) {
      message += "\nParking Lot Number " + parkingLotNumber + ": {\n"
      for (parkingLotSpot <- 0 until listOfList.get(parkingLotNumber).size()) {
        message += "\tVehicle: {"
        val driver: Driver = parkingLots.get(parkingLotNumber).parkingLot(parkingLotSpot)
        message += "\n\t\tNumber Plate: " + driver.vehicle.getNumberPlate() + ", "
        message += "\n\t\tPA Name: " + driver.getName() + ", "
        message += "\n\t\tLocation: " + parkingLotSpot

        if (parkingLotSpot != listOfList.get(parkingLotNumber).size() - 1) {
          message += "\n\t},\n"
        } else {
          message += "}\n"
        }
      }
      message += "},\n"
    }
    message = message.substring(0, message.length - 2)
    message
  }
}
