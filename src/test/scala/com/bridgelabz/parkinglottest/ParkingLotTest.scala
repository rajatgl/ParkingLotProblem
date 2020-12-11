package com.bridgelabz.parkinglottest

import com.bridgelabz.parkinglotsolution.{ParkingLot, Vehicle}
import org.scalatest.FunSuite

class ParkingLotTest extends FunSuite {
  test("givenAVehicleWhenParkedShouldReturnTrue"){
    val parkingLot = new ParkingLot()
    val isParked = parkingLot.park(new Vehicle)
    assert(isParked == true)
  }
  test("givenAVehicleWhenUnParkedShouldReturnTrue"){
    val parkingLot = new ParkingLot()
    parkingLot.park(new Vehicle)
    val isUnParked = parkingLot.unPark(0)
    assert(isUnParked == true)
  }
}
