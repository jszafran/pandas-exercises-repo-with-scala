package dev.jszafran
package parsers

case class CountryAlcoholData(
                      name: String,
                      beerServings: Int,
                      spiritServings: Int,
                      wineServings: Int,
                      totalLitresOfPureAlcohol: Double,
                      continent: String
                      )

object AlcoholConsumptionParser {
  def parseData(filePath: String): List[CountryAlcoholData] = {
    val source = io.Source.fromFile(filePath)
    val lines = source.getLines.drop(1)
    val countriesAlcoholData = for (line <- lines) yield {
      val ls = line.split(",")
      CountryAlcoholData(
        name = ls(0),
        beerServings = ls(1).toInt,
        spiritServings = ls(2).toInt,
        wineServings = ls(3).toInt,
        totalLitresOfPureAlcohol = ls(4).toDouble,
        continent = ls(5)
      )
    }
    countriesAlcoholData.toList
  }
}
