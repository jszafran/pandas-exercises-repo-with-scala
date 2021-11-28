package dev.jszafran
package fictionalarmy

import parsers.FictionalArmyParser

object FictionalArmy extends App {
  val regiments = FictionalArmyParser.parseData("./datasets/fictional_army.csv")
  println(regiments(0))
}
