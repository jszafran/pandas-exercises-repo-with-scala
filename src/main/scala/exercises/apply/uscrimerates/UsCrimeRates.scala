package dev.jszafran
package exercises.apply.uscrimerates

import parsers.USCrimeRatesParser

object UsCrimeRates extends App {
  val crimeRates = USCrimeRatesParser.parseData("./datasets/US_Crime_Rates_1960_2014.csv")
  println(crimeRates.head)
}
