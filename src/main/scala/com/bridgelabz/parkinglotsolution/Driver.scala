package com.bridgelabz.parkinglotsolution

import com.bridgelabz.parkinglotsolution.design.{Message, Observer}

class Driver extends Observer {
  var vehicle: Vehicle = new Vehicle

  override def update(message: Message): Unit = {
    println(message.getMessageContent)
  }
}
