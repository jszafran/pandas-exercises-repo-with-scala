package dev.jszafran
package exercises.grouping.alcoholconsumption

import parsers.AlcoholConsumptionParser

import dev.jszafran.utils.printBreak

object AlcoholConsumption extends App {
  val countriesAlcData = AlcoholConsumptionParser.parseData("./datasets/AlcoholConsumption.csv")

  // Q1
  println("Q: Which continent drinks more beer on average")
  val q1Answer = countriesAlcData
    .groupBy(_.continent)
    .transform((k, v) => {
      v.map(_.beerServings).sum / v.length
    })
    .toSeq
    .sortBy(_._2)
    .reverse

  println(s"On average, the continent with biggest beer consumption is ${q1Answer.head._1} (${q1Answer.head._2})")
  println("Contenders:")
  q1Answer.drop(1).foreach(println)
  printBreak()

  // Q2
  println("Q: For each continent print the statistics for wine consumption.")
  val q2Answer = countriesAlcData
    .groupBy(_.continent)
    .transform((k, v) => v.map(_.wineServings).sum / v.length)
    .toSeq
  println("Wine consumption data per continent:")
  q2Answer.foreach(println)
}
