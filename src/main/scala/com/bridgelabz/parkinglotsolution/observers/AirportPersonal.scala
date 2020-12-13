package com.bridgelabz.parkinglotsolution.observers

import com.bridgelabz.parkinglotsolution.design.{Message, Observer}

class AirportPersonal extends Observer{
  override def update(message: Message): Unit = {
    println(message.getMessageContent)
  }
}
