package com.bridgelabz.parkinglotsolution.observers

import com.bridgelabz.parkinglotsolution.design.{Message, Observer}
import com.typesafe.scalalogging.Logger

/**
 * Created on 12/11/2020.
 * Class: ParkingLotOwner.scala
 * Author: Rajat G.L.
 */
object ParkingLotOwner extends Observer {

  var logger: Logger = Logger("Parking Lot Owner")

  def getOpinionOnParkingSpot: Int = {

    // Necessary for console operation
    // scalastyle:off regex
    println("Hey Sanjay! Where would you want the Parking Attendant to park?")
    scala.io.StdIn.readInt()
  }

  override def update(message: Message): Unit = {
    logger.info("Parking Lot Owner: " + message.getMessageContent)
  }
}
