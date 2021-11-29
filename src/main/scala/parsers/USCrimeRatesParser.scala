package dev.jszafran
package parsers

case class YearlyCrimeStats(
                           year: Int,
                           population: Int,
                           total: Int,
                           violent: Int,
                           property: Int,
                           murder: Int,
                           forcibleRape: Int,
                           robbery: Int,
                           aggravatedAssault: Int,
                           burglary: Int,
                           larcenyTheft: Int,
                           vehicleTheft: Int
                           )

object USCrimeRatesParser {
  private def lineParsingFunc(line: String): YearlyCrimeStats = {
    val ls = line.split(",")
    YearlyCrimeStats(
      year = ls(0).toInt,
      population = ls(1).toInt,
      total = ls(2).toInt,
      violent = ls(3).toInt,
      property = ls(4).toInt,
      murder = ls(5).toInt,
      forcibleRape = ls(6).toInt,
      robbery = ls(7).toInt,
      aggravatedAssault = ls(8).toInt,
      burglary = ls(9).toInt,
      larcenyTheft = ls(10).toInt,
      vehicleTheft = ls(11).toInt
    )
  }

  def parseData(filePath: String): List[YearlyCrimeStats] = GenericTextParser.parseData[YearlyCrimeStats](
    filePath = filePath,
    lineParsingFunc = lineParsingFunc,
  )
}
