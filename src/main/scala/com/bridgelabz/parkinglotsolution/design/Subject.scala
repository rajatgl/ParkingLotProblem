package com.bridgelabz.parkinglotsolution.design

trait Subject {

  def attach(observer: Observer): Unit

  def detach(observer: Observer): Unit

  def notifyUpdate(message: Message): Unit

  def notifyUpdate(message: Message, classType: String): Unit
}
