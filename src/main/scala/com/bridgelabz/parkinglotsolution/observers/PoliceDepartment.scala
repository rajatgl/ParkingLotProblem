package com.bridgelabz.parkinglotsolution.observers

import com.bridgelabz.parkinglotsolution.design.{Message, Observer}
import com.typesafe.scalalogging.Logger

/**
 * Created on 12/16/2020.
 * Class: PoliceDepartment.scala
 * Author: Rajat G.L.
 */
object PoliceDepartment extends Observer{

  var logger: Logger = Logger("Police Department")

  override def update(message: Message): Unit = {
    logger.info("Police Department: " + message.getMessageContent)
  }
}
