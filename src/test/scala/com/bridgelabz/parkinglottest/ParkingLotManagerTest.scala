package com.bridgelabz.parkinglottest

import java.io.{ByteArrayOutputStream, StringReader}
import java.util

import com.bridgelabz.parkinglotsolution.design.Message
import com.bridgelabz.parkinglotsolution.{ParkingLot, ParkingLotManager}
import com.bridgelabz.parkinglotsolution.observers.{AirportPersonal, Driver, ParkingAttendant}
import org.scalatest.FunSuite

class ParkingLotManagerTest extends FunSuite {

  test("givenParkingLotManagerShouldAddParkingLot"){

    ParkingLotManager.addParkingLot(2)
    assert(ParkingLotManager.parkingLots.size() == 2)
  }

  test("givenParkingLotManagerShouldParkCars"){

    ParkingLotManager.addParkingLot()
    val driver = new Driver("Rajat")
    driver.setVehicle("blue", "bmw", "KA")
    ParkingLotManager.park(driver)
  }

  test("givenParkingLotManagerShouldParkCarsOfHandicapDrivers"){

    ParkingLotManager.addParkingLot()
    val driver = new ParkingAttendant("Rajat")
    driver.setVehicle("blue", "bmw", "KA", true)

    val inputStr =
      """|1
      """.stripMargin
    val in = new StringReader(inputStr)
    val out = new ByteArrayOutputStream()
    Console.withOut(out) {
      Console.withIn(in) {
        ParkingLotManager.handicappedPark(driver)
      }
    }
  }

  test("givenParkedCarShouldBeAbleToDepart"){
    ParkingLotManager.addParkingLot()
    val driver = new Driver("Rajat")
    driver.setVehicle("blue", "bmw", "KA")
    val parkingLotIndex = ParkingLotManager.park(driver)

    assert(ParkingLotManager.depart("KA", parkingLotIndex))
  }

  test("givenParkingLotsGetAllCarsShouldBeEmpty"){

    ParkingLotManager.parkingLots = new util.ArrayList[ParkingLot]
    ParkingLotManager.addParkingLot()
    assert(ParkingLotManager.getAllCars().get(0).isEmpty)
  }

  test("givenParkingLotsGetAllCarsOfGivenColorShouldBeEmpty"){

    ParkingLotManager.parkingLots = new util.ArrayList[ParkingLot]
    ParkingLotManager.addParkingLot()
    assert(ParkingLotManager.getAllCars("blue").get(0).isEmpty)
  }

  test("givenParkingLotsGetAllCarsOfGivenColorAndMakeShouldBeEmpty"){

    ParkingLotManager.parkingLots = new util.ArrayList[ParkingLot]
    ParkingLotManager.addParkingLot()
    assert(ParkingLotManager.getAllCars("blue", "bmw").get(0).isEmpty)
  }

  test("givenParkingLotsGetAllCarsOfGivenMakeShouldBeEmpty"){

    ParkingLotManager.parkingLots = new util.ArrayList[ParkingLot]
    ParkingLotManager.addParkingLot()
    assert(ParkingLotManager.getAllCarsWithMake("bmw").get(0).isEmpty)
  }

  test("givenParkingLotsGetAllHandicapCarsShouldBeEmpty"){

    ParkingLotManager.parkingLots = new util.ArrayList[ParkingLot]
    ParkingLotManager.addParkingLot()
    assert(ParkingLotManager.getAllHandicapCars(false).get(0).isEmpty)
  }

  test("givenParkingLotsGetAllCarsInGivenDurationShouldBeEmpty"){

    ParkingLotManager.parkingLots = new util.ArrayList[ParkingLot]
    ParkingLotManager.addParkingLot()
    assert(ParkingLotManager.getAllCars(10).get(0).isEmpty)
  }

  test("printParkingAllotmentShouldReturnAString"){
    ParkingLotManager.addParkingLot()
    ParkingLotManager.addParkingLot()
    val driver = new Driver("Rajat")
    driver.setVehicle("blue", "bmw", "KA")
    ParkingLotManager.park(driver)
    ParkingLotManager.park(driver)
    ParkingLotManager.park(driver)

    val listOfList = ParkingLotManager.getAllCars()

    assert(ParkingLotManager.getParkingAllotment(listOfList).isInstanceOf[String])
  }

  test("notifyAirportPersonelShouldPrintMessage"){
    (new AirportPersonal).update(new Message("Hi"))
  }
}
