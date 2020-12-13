package com.bridgelabz.parkinglotsolution

import com.bridgelabz.parkinglotsolution.design.{Message, Observer}

/**
 * Created on 12/11/2020.
 * Class: ParkingLotOwner.scala
 * Author: Rajat G.L.
 */
object ParkingLotOwner extends Observer{
  override def update(message: Message): Unit = {
    println(message.getMessageContent)
  }
}
