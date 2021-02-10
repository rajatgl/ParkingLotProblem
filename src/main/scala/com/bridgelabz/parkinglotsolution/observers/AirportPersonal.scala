package com.bridgelabz.parkinglotsolution.observers

import com.bridgelabz.parkinglotsolution.design.{Message, Observer}
import com.typesafe.scalalogging.Logger

class AirportPersonal extends Observer{
  var logger: Logger = Logger("Airport Personal")
  override def update(message: Message): Unit = {
    logger.info("Airport Personal: " + message.getMessageContent)
  }
}
