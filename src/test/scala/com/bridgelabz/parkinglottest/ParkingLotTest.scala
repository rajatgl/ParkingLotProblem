package com.bridgelabz.parkinglottest

import java.io.{ByteArrayOutputStream, StringReader}

import com.bridgelabz.parkinglotsolution.observers.{Driver, ParkingAttendant}
import com.bridgelabz.parkinglotsolution.{ParkingLot, ParkingLotManager, Vehicle}
import org.scalatest.FunSuite


class ParkingLotTest extends FunSuite {
  //UC1
  test("givenAVehicleWhenParkedShouldReturnTrue") {
    val parkingLot = new ParkingLot()
    val driver = new Driver("Rajat")
    driver.setVehicle("blue", "bmw", "MH")
    assert(parkingLot.park(driver))
  }
  //UC2
  test("givenAVehicleWhenUnParkedShouldReturnTrue") {
    val parkingLot = new ParkingLot()
    val driver = new Driver("Rajat")
    driver.setVehicle("blue", "bmw", "KA")
    parkingLot.park(driver)
    assert(parkingLot.depart(0))
  }
  //UC3
  test("givenWhenParkingLotIsFullShouldInformOwner") {
    val parkingLot = new ParkingLot
    parkingLot.parkingLotSize = 0
    assert(parkingLot.isFull)
  }

  //UC4
  test("givenWHenParkingLotIsFullShouldInformAirportPersonal") {
    val parkingLot = new ParkingLot
    parkingLot.parkingLotSize = 0
    assert(parkingLot.isFull)
  }

  //UC5
  test("givenWhenParkingLotSpaceAvailableAfterFullShouldReturnTrue") {
    val parkingLot = new ParkingLot
    parkingLot.parkingLotSize = 1
    val driver = new Driver("Rajat")
    driver.setVehicle("blue", "bmw", "KA")
    parkingLot.park(driver)
    assert(parkingLot.depart(parkingSpot = 0))
  }
  //UC6
  test("givenParkingLotWhenAskedWhereToParkAndIfAlreadyParkingFullOrParkedShouldReturnFalse") {
    val parkingLot = new ParkingLot
    parkingLot.parkingLotSize = 1
    val driver = new Driver("Rajat")
    driver.setVehicle("blue", "bmw", "KA")
    parkingLot.park(driver)
    val driver1 = new Driver("Rajat")
    driver1.setVehicle("blue", "bmw", "KA")
    assert(!parkingLot.park(driver1))
  }
  //UC7
  test("givenVehicleIfFoundShouldReturnTrue") {
    val parkingLot = new ParkingLot
    parkingLot.parkingLotSize = 1
    val driver = new Driver("Rajat")
    driver.setVehicle("blue", "bmw", "KA")
    parkingLot.park(driver)
    assert(parkingLot.depart(0))
    }
  //Given a vehicle if not found should return false
  test("givenVehicleIfNotFoundShouldReturnFalse") {
    val parkingLot = new ParkingLot
    parkingLot.parkingLotSize = 1
    val driver = new Driver("Rajat")
    driver.setVehicle("blue", "bmw", "KA")
    parkingLot.park(driver)
    assert(!parkingLot.depart(1))
  }
  //UC8
  test("givenVehicleWhenParkedShouldReturnTime"){
    val parkingLot = new ParkingLot
    parkingLot.parkingLotSize = 1
    val driver = new Driver("Rajat")
    driver.setVehicle("blue", "bmw", "KA")
    parkingLot.park(driver)
    assert(driver.vehicle.getArrivalTime()!=0)
  }
  //UC12
  test("givenWhiteVehicleShouldReturnLotWhereFound"){
    val parkingLot = new ParkingLot
    parkingLot.parkingLotSize = 1
    val driver = new Driver("Rajat")
    driver.setVehicle("white", "bmw", "KA")
    parkingLot.park(driver)
    assert(!parkingLot.getAllCars("white").isEmpty)
  }
  //UC13
  test("givenBlueVehicleWithBrandToyotaShouldReturnLotWhereFound"){
    val parkingLot = new ParkingLot
    parkingLot.parkingLotSize = 1
    val driver = new Driver("Rajat")
    driver.setVehicle("blue", "toyota", "KA")
    parkingLot.park(driver)
    assert(!parkingLot.getAllCars("blue","toyota").isEmpty)
  }
  //UC14
  test("givenVehiclesWithBMW-MakeShouldReturnListOfSpotsOccupied"){
    val parkingLot = new ParkingLot
    parkingLot.parkingLotSize = 1
    val driver = new Driver("Rajat")
    driver.setVehicle("blue", "bmw", "KA")
    parkingLot.park(driver)
    assert(!parkingLot.getAllCarsWithMake("bmw").isEmpty)
  }
  //UC15
  test("givenVehiclesParkedBefore30SecondsShouldReturnListOfSpotsOccupied"){
    val parkingLot = new ParkingLot
    parkingLot.parkingLotSize = 1
    val driver = new Driver("Rajat")
    driver.setVehicle("blue", "bmw", "KA")
    parkingLot.park(driver)
    assert(!parkingLot.getAllCars(30).isEmpty)
  }
  //UC16
  test("givenVehiclesWithHandicapDriversShouldReturnListOfSpotsOccupied"){
    val parkingLot = new ParkingLot
    parkingLot.parkingLotSize = 1
    val driver = new Driver("Rajat")
    driver.setVehicle("blue", "bmw", "KA",true)
    parkingLot.park(driver)
    assert(!parkingLot.getAllHandicapCars().isEmpty)
  }
  //UC17
  test("givenVehiclesShouldReturnListOFSpotsOccupied"){
    val parkingLot = new ParkingLot
    parkingLot.parkingLotSize = 1
    val driver = new Driver("Rajat")
    driver.setVehicle("blue", "bmw", "KA",true)
    parkingLot.park(driver)
    assert(!parkingLot.getAllCars().isEmpty)
  }

  //Additional Test Cases
  test("givenParkingLotInitializationShouldReturnEmptyLot"){
    val parkingLot = new ParkingLot()
    assert(parkingLot.getCurrentlyParked == 0)
  }

  test("givenHandicapDriverShouldParkCloseBy"){
    val parkingLot = new ParkingLot()
    val driver = new ParkingAttendant("Rajat")
    driver.setVehicle("blue", "lexus", "MH", true)
    val inputStr =
      """|1
      """.stripMargin
    val in = new StringReader(inputStr)
    val out = new ByteArrayOutputStream()
    Console.withOut(out) {
      Console.withIn(in) {
        parkingLot.park(driver)
      }
    }

    assert(parkingLot.getCurrentlyParked != 0)
  }

  test("givenHandicapDriverAndWrongOwnerChoiceShouldParkCloseBy"){
    val parkingLot = new ParkingLot()
    val driver = new ParkingAttendant("Rajat")
    driver.setVehicle("blue", "lexus", "MH", true)
    val inputStr =
      """|-1
         |0
      """.stripMargin
    val in = new StringReader(inputStr)
    val out = new ByteArrayOutputStream()
    Console.withOut(out) {
      Console.withIn(in) {
        parkingLot.park(driver)
      }
    }

    assert(parkingLot.getCurrentlyParked != 0)
  }

  test("givenEmptyParkingLotShouldReturnNearestParkingSpotAsZero"){
    val parkingLot = new ParkingLot()
    assert(parkingLot.nearestFreeParkingSpot() == 0)
  }

  test("givenFullParkingLotShouldReturnNearestParkingSpotAsParkingLotSize"){
    val parkingLot = new ParkingLot()
    parkingLot.parkingLotSize = 0
    assert(parkingLot.nearestFreeParkingSpot() == 0)
  }

  test("givenValidNumberPlateAllowDriverToDepart"){
    val parkingLot = new ParkingLot()
    val driver = new Driver("Rajat")
    driver.setVehicle("blue", "lexus", "MH")
    parkingLot.park(driver)

    assert(parkingLot.depart("MH"))
  }

  test("givenInvalidNumberPlateDenyDriverToDepart"){
    val parkingLot = new ParkingLot()
    val driver = new Driver("Rajat")
    driver.setVehicle("blue", "lexus", "MH")
    parkingLot.park(driver)

    assert(!parkingLot.depart("KA"))
  }
}
