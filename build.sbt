name := "ParkingLotProblem"

version := "0.1"

scalaVersion := "2.12.2"

coverageEnabled := true

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.8" % Test,
  //scala log feature
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
  "ch.qos.logback" % "logback-classic" % "1.2.3" % Runtime,
  "ch.qos.logback" % "logback-core" % "1.2.3"
)