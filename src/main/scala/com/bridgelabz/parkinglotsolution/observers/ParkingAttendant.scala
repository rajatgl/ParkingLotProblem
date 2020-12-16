package com.bridgelabz.parkinglotsolution.observers
import com.bridgelabz.parkinglotsolution.design.Message

class ParkingAttendant(name: String) extends Driver(name) {

  override def update(message: Message): Unit = {
    println("Parking Attendant: " + message.getMessageContent)
  }
}
