package com.bridgelabz.parkinglotsolution.observers

import com.bridgelabz.parkinglotsolution.design.{Message, Observer}

/**
 * Created on 12/11/2020.
 * Class: ParkingLotOwner.scala
 * Author: Rajat G.L.
 */
object ParkingLotOwner extends Observer {

  def getOpinionOnParkingSpot: Int = {

    println("Hey Sanjay! Where would you want the Parking Attendant to park?")
    scala.io.StdIn.readInt()
  }

  override def update(message: Message): Unit = {
    println(message.getMessageContent)
  }
}
