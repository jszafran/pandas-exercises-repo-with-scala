package dev.jszafran
package exercises.apply.uscrimerates

import parsers.USCrimeRatesParser

object UsCrimeRates extends App {
  val crimeRates = USCrimeRatesParser.parseData("./datasets/US_Crime_Rates_1960_2014.csv")

  // Q1
  println("Q: Group the year by decades and sum the values")

  // Q2
 println("Q: What is the most dangerous decade to live in the US?")

}
