package dev.jszafran
package exercises.grouping.alcoholconsumption

import parsers.AlcoholConsumptionParser

import utils.{medianInt, medianDouble, printBreak}

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
  printBreak()

  // Q4
  println("Q: Print the median alcohol consumption per continent for every column")
  val q4Answer = countriesAlcData
    .groupBy(_.continent)
    .transform((k, v) => {
      ContinentAlcoholConsumptionStats(
        name = k,
        beerServings = medianInt(v.map(_.beerServings)),
        wineServings = medianInt(v.map(_.wineServings)),
        spiritServings = medianInt(v.map(_.spiritServings)),
        totalLitresOfPureAlcohol = medianDouble(v.map(_.totalLitresOfPureAlcohol))
      )
    })
    .toSeq
    .map(_._2)
  q4Answer.foreach(_.displayStats())
  printBreak()

  // Q5
  println("Q: Print the mean, min and max values for spirit consumption.")
  case class MeanMaxMin(continentCode: String, mean: Double, min: Int, max: Int) {
    def display(): Unit = println(s"Continent $continentCode | mean: $mean | min: $min | max: $max")
  }
  val q5Answer = countriesAlcData
    .groupBy(_.continent)
    .transform((k, v) => {
      val ss = v.map(_.spiritServings)
      MeanMaxMin(
        continentCode = k,
        mean = ss.sum / v.length.toDouble,
        min = ss.min,
        max = ss.max
      )
    })
    .toSeq
    .map(_._2)
  q5Answer.foreach(_.display())
}
