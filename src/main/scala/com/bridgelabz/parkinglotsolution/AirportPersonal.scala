package com.bridgelabz.parkinglotsolution

import com.bridgelabz.parkinglotsolution.design.{Message, Observer}

class AirportPersonal extends Observer{
  override def update(message: Message): Unit = {
    println(message.getMessageContent)
  }
}
