package com.bridgelabz.parkinglotsolution.observers
import com.bridgelabz.parkinglotsolution.design.Message

class ParkingAttendant extends Driver {

  def ParkingAttendant(): Unit ={

  }

  override def update(message: Message): Unit = {
    println("Parking Attendant: " + message.getMessageContent)
  }
}
