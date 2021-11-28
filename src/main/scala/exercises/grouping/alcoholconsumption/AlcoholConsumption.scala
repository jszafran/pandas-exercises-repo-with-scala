package dev.jszafran
package exercises.grouping.alcoholconsumption

import parsers.AlcoholConsumptionParser

object AlcoholConsumption extends App {
  val countriesAlcData = AlcoholConsumptionParser.parseData("./datasets/AlcoholConsumption.csv")
}
