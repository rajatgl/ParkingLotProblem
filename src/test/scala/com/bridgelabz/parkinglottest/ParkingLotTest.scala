package com.bridgelabz.parkinglottest

import com.bridgelabz.parkinglotsolution.{Driver, ParkingLot, ParkingLotOwner, Vehicle}
import org.scalatest.FunSuite

class ParkingLotTest extends FunSuite {
  //UC1
  test("givenAVehicleWhenParkedShouldReturnTrue") {
    val parkingLot = new ParkingLot()
    assert(parkingLot.park(new Driver))
  }
  //UC2
  test("givenAVehicleWhenUnParkedShouldReturnTrue") {
    val parkingLot = new ParkingLot()
    parkingLot.park(new Driver)
    assert(parkingLot.depart(0))
  }
  //UC3
  test("givenWhenParkingLotIsFullShouldInformOwner") {
    val parkingLot = new ParkingLot
    parkingLot.parkingLotSize = 0
    assert(parkingLot.isFull)
  }

  //UC4
  test("givenWHenParkingLotIsFullShouldInformAirportPersonal"){
    val parkingLot = new ParkingLot
    parkingLot.parkingLotSize = 0
    assert(parkingLot.isFull)
  }

}
