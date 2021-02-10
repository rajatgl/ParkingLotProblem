package com.bridgelabz.parkinglotsolution.observers
import com.bridgelabz.parkinglotsolution.design.Message
import com.typesafe.scalalogging.Logger

class ParkingAttendant(name: String) extends Driver(name) {

  var loggerDerived: Logger = Logger("Parking Lot Attendant")

  override def update(message: Message): Unit = {
    loggerDerived.info("Parking Attendant: " + message.getMessageContent)
  }
}
