package dev.jszafran
package exercises.grouping.alcoholconsumption

import parsers.AlcoholConsumptionParser

import dev.jszafran.utils.printBreak

case class ContinentAlcoholConsumptionStats(
                                             name: String,
                                             beerServings: Double,
                                             spiritServings: Double,
                                             wineServings: Double,
                                             totalLitresOfPureAlcohol: Double,
                                           ) {
  def displayStats(): Unit = {
    println(s"Continent $name | beer: $beerServings | spirit: $spiritServings | wine: $wineServings | total pure alc: $totalLitresOfPureAlcohol")
  }
}

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
  printBreak()

  // Q3
  println("Q: Print the mean alcohol consumption per continent for every column")
  val q3Answer = countriesAlcData
    .groupBy(_.continent)
    .transform((k, v) => {
      ContinentAlcoholConsumptionStats(
        name = k,
        beerServings = v.map(_.beerServings).sum / v.length.toDouble,
        wineServings = v.map(_.wineServings).sum / v.length.toDouble,
        spiritServings = v.map(_.spiritServings).sum / v.length.toDouble,
        totalLitresOfPureAlcohol = v.map(_.totalLitresOfPureAlcohol).sum / v.length.toDouble
      )
    })
    .toSeq
    .map(_._2)
  q3Answer.foreach(_.displayStats())
}
