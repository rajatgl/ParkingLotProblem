package com.bridgelabz.parkinglotsolution.observers

import com.bridgelabz.parkinglotsolution.design.{Message, Observer}

/**
 * Created on 12/16/2020.
 * Class: PoliceDepartment.scala
 * Author: Rajat G.L.
 */
object PoliceDepartment extends Observer{
  override def update(message: Message): Unit = {
    println("Police Department: " + message.getMessageContent)
  }
}
