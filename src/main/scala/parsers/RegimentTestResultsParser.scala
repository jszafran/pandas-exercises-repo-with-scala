package dev.jszafran
package parsers

case class SoldierTestResults(
                               regiment: String,
                               companyName: String,
                               name: String,
                               preTestScore: Int,
                               postTestScore: Int
                             )

object RegimentTestResultsParser {
  private def lineParsingFunc(line: String) : SoldierTestResults = {
    val ls = line.split(",")
    SoldierTestResults(
      regiment = ls(0),
      companyName = ls(1),
      name = ls(2),
      preTestScore = ls(3).toInt,
      postTestScore = ls(4).toInt
    )
  }

  def parseData(filePath: String): List[SoldierTestResults] = {
    GenericTextParser.parseData[SoldierTestResults](
      filePath = filePath,
      lineParsingFunc = lineParsingFunc
    )
  }
}
