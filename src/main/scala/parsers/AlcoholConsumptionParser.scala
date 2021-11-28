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
  private def lineParsingFunc(line: String): CountryAlcoholData = {
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

  def parseData(filePath: String): List[CountryAlcoholData] = {
    GenericTextParser.parseData[CountryAlcoholData](
      filePath = filePath,
      lineParsingFunc = lineParsingFunc
    )
  }
}
